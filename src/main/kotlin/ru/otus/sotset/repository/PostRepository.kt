package ru.otus.sotset.repository

import ru.otus.sotset.model.Post
import java.util.UUID

interface PostRepository {
    fun create(text: String, authorId: UUID): Post
    fun getById(id: UUID): Post?
    fun update(post: Post)
    fun delete(id: UUID)
    fun getFriendsPostsForUser(userId: UUID, offset: Int, limit: Int): List<Post>
}
