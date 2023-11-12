package ru.otus.sotset.service.post.cache

import org.springframework.context.annotation.Primary
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import ru.otus.sotset.model.Post
import java.util.*

@Component
@Primary
class PostFeedRedisCache(
    private val redisTemplate: RedisTemplate<UUID, Post>
) : PostFeedCache {

    private val redisListOps = redisTemplate.opsForList()

    override fun get(userId: UUID): List<Post>? {
        val posts = redisListOps.range(userId, 0, -1)
        return if (posts?.isEmpty() == false) posts else null
    }

    override fun set(userId: UUID, posts: List<Post>) {
        redisTemplate.delete(userId)
        if (posts.isNotEmpty()) {
            redisListOps.rightPushAll(userId, posts)
        }
    }

    override fun add(userId: UUID, post: Post) {
        redisListOps.leftPush(userId, post)
        redisListOps.trim(userId, 0, CACHE_LIMIT.toLong())
    }

    override fun update(userId: UUID, post: Post) {
        val posts = get(userId) ?: return
        val oldPost = posts.find { it.id == post.id } ?: return
        val index = redisListOps.indexOf(userId, oldPost) ?: return
        redisListOps.set(userId, index, post)
    }

    override fun delete(userId: UUID, post: Post) {
        redisListOps.remove(userId, 1, post)
    }

}
