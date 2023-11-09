package ru.otus.sotset.repository.impl

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import ru.otus.sotset.model.User
import ru.otus.sotset.repository.FriendRepository

const val ADD_FRIEND = """
    INSERT INTO friends
    VALUES (:owner_id, :friend_id)
    ON CONFLICT DO NOTHING
"""

const val REMOVE_FRIEND = """
    DELETE FROM friends
    WHERE (owner_id, friend_id) = (:owner_id, :friend_id)
"""

@Component
class FriendRepositoryImpl(
    val jdbcTemplate: NamedParameterJdbcTemplate
) : FriendRepository {

    override fun setFriend(owner: User, friend: User) {
        jdbcTemplate.update(ADD_FRIEND, mapOf(
            "owner_id" to owner.id,
            "friend_id" to friend.id)
        )
    }

    override fun removeFriend(owner: User, friend: User) {
        jdbcTemplate.update(REMOVE_FRIEND, mapOf(
            "owner_id" to owner.id,
            "friend_id" to friend.id)
        )
    }
}
