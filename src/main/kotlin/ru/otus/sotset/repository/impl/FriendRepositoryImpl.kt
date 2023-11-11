package ru.otus.sotset.repository.impl

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import ru.otus.sotset.model.User
import ru.otus.sotset.repository.FriendRepository
import java.util.*

const val INSERT_FRIEND = """
    INSERT INTO friends
    VALUES (:owner_id, :friend_id)
    ON CONFLICT DO NOTHING
"""

const val REMOVE_FRIEND = """
    DELETE FROM friends
    WHERE (owner_id, friend_id) = (:owner_id, :friend_id)
"""

const val SELECT_SUBSCRIBERS_OF_USER = """
    SELECT owner_id
    FROM friends
    WHERE friend_id = :user_id
"""

@Component
class FriendRepositoryImpl(
    @Qualifier("masterJdbcTemplate") private val masterJdbcTemplate: NamedParameterJdbcTemplate,
    @Qualifier("slaveJdbcTemplate") private val slaveJdbcTemplate: NamedParameterJdbcTemplate
) : FriendRepository {

    override fun setFriend(owner: User, friend: User) {
        masterJdbcTemplate.update(INSERT_FRIEND, mapOf(
            "owner_id" to owner.id,
            "friend_id" to friend.id)
        )
    }

    override fun removeFriend(owner: User, friend: User) {
        masterJdbcTemplate.update(REMOVE_FRIEND, mapOf(
            "owner_id" to owner.id,
            "friend_id" to friend.id)
        )
    }

    override fun getSubscribersOfUser(userId: UUID): List<UUID> =
        slaveJdbcTemplate.queryForList(SELECT_SUBSCRIBERS_OF_USER,
            mapOf("user_id" to userId),
            UUID::class.java
        )

}
