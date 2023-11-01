package ru.otus.sotset.repository

import ru.otus.sotset.model.User
import java.util.UUID

interface UserRepository {
    fun create(user: User)
    fun find(id: UUID): User?
    fun search(firstName: String, lastName: String): List<User>
}
