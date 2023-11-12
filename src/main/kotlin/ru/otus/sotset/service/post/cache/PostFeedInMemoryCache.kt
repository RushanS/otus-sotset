package ru.otus.sotset.service.post.cache

import org.springframework.stereotype.Component
import ru.otus.sotset.model.Post
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Component
class PostFeedInMemoryCache : PostFeedCache {

    private val cache = ConcurrentHashMap<UUID, LinkedList<Post>>()

    override fun get(userId: UUID): List<Post>? = cache[userId]

    override fun set(userId: UUID, posts: List<Post>) {
        if (posts.isNotEmpty()) {
            cache[userId] = LinkedList(posts)
        }
    }

    override fun add(userId: UUID, post: Post) {
        val cachedPosts = cache.computeIfAbsent(userId) { LinkedList() }
        while (cachedPosts.size >= CACHE_LIMIT) {
            cachedPosts.removeLast()
        }
        cachedPosts.addFirst(post)
    }

    override fun update(userId: UUID, post: Post) {
        val cachedPosts = cache[userId] ?: return
        val index = cachedPosts.indexOfFirst { it.id == post.id }
        cachedPosts[index] = post
    }

    override fun delete(userId: UUID, post: Post) {
        val cachedPosts = cache[userId] ?: return
        cachedPosts.removeIf { it.id == post.id }
    }
}
