package ru.otus.sotset.repository

import ru.otus.sotset.model.User

interface FriendRepository {
    fun setFriend(owner: User, friend: User)
    fun removeFriend(owner: User, friend: User)
}
