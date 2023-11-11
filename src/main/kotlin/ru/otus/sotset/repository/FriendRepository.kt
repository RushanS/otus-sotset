package ru.otus.sotset.repository

import ru.otus.sotset.model.User
import java.util.UUID

interface FriendRepository {
    fun setFriend(owner: User, friend: User)
    fun removeFriend(owner: User, friend: User)
    fun getSubscribersOfUser(userId: UUID): List<UUID>
}
