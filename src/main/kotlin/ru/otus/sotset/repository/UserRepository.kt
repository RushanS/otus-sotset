package ru.otus.sotset.repository

import ru.otus.sotset.model.User

interface UserRepository {
    fun create(user: User)
    fun find(id: String): User?
}