package ru.otus.sotset.service.post.cache

import ru.otus.sotset.model.Post
import java.util.*

const val CACHE_LIMIT = 1000

interface PostFeedCache {
    fun get(userId: UUID): List<Post>?
    fun set(userId: UUID, posts: List<Post>)
    fun add(userId: UUID, post: Post)
    fun update(userId: UUID, post: Post)
    fun delete(userId: UUID, post: Post)
}
