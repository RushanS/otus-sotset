package ru.otus.sotset.repository.impl

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Component
import ru.otus.sotset.model.Post
import ru.otus.sotset.repository.PostRepository
import java.sql.Timestamp
import java.util.*

const val INSERT_POST = """
    INSERT INTO posts(
        text,
        author_id
    ) VALUES (
        :text,
        :author_id
    ) RETURNING 
        id,
        created_at
"""

const val SELECT_POST_BY_ID = """
    SELECT
        id,
        text,
        author_id,
        created_at
    FROM posts
    WHERE id = :id
"""

const val UPDATE_POST = """
    UPDATE posts
    SET 
        text = :text
    WHERE id = :id
"""

const val DELETE_POST = """
    DELETE FROM posts
    WHERE id = :id
"""

const val SELECT_FRIENDS_POSTS = """
    SELECT 
        id,
        text,
        author_id,
        created_at
    FROM posts
    WHERE author_id IN (
        SELECT friend_id FROM friends
        WHERE owner_id = :user_id
    )
    ORDER BY created_at DESC
    OFFSET :offset
    LIMIT :limit;
"""

@Component
class PostRepositoryImpl(
    @Qualifier("masterJdbcTemplate") private val masterJdbcTemplate: NamedParameterJdbcTemplate,
    @Qualifier("slaveJdbcTemplate") private val slaveJdbcTemplate: NamedParameterJdbcTemplate
) : PostRepository {

    private val rowMapper = RowMapper { rs, _ ->
        Post(
            id = rs.getObject("id", UUID::class.java),
            text = rs.getString("text"),
            authorId = rs.getObject("author_id", UUID::class.java),
            createdAt = rs.getTimestamp("created_at").toLocalDateTime()
        )
    }

    override fun create(text: String, authorId: UUID): Post {
        val params = mapOf(
            "text" to text,
            "author_id" to authorId
        )
        val keyHolder = GeneratedKeyHolder()
        masterJdbcTemplate.update(INSERT_POST, MapSqlParameterSource(params), keyHolder)
        val inserted = keyHolder.keys ?: throw IllegalStateException("No inserted data")
        val id = inserted["id"] as UUID
        val createdAt = inserted["created_at"] as Timestamp
        return Post(id, text, authorId, createdAt.toLocalDateTime())
    }

    override fun getById(id: UUID): Post? {
        val list = masterJdbcTemplate.query(SELECT_POST_BY_ID, mapOf("id" to id), rowMapper)
        return if (list.isEmpty()) null else list[0]
    }

    override fun update(post: Post) {
        masterJdbcTemplate.update(UPDATE_POST, mapOf(
            "id" to post.id,
            "text" to post.text
        ))
    }

    override fun delete(id: UUID) {
        masterJdbcTemplate.update(DELETE_POST, mapOf("id" to id,))
    }

    override fun getFriendsPostsForUser(userId: UUID, offset: Int, limit: Int): List<Post> {
        return slaveJdbcTemplate.query(SELECT_FRIENDS_POSTS, mapOf(
            "user_id" to userId,
            "offset" to offset,
            "limit" to limit
        ), rowMapper)
    }

}
