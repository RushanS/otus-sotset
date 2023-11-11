package ru.otus.sotset.service.post.cache

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import ru.otus.sotset.repository.PostRepository
import ru.otus.sotset.repository.UserRepository

const val BATCH_SIZE = 100

@Service
class PostWarmupService(
    val postRepository: PostRepository,
    val userRepository: UserRepository,
    val postFeedCache: PostFeedCache
) {

    @Async
    fun warmup() {
        var offset = 0
        do {
            val users = userRepository.findAll(offset, BATCH_SIZE)
            offset += BATCH_SIZE
            users.forEach { user ->
                val posts = postRepository.getFriendsPostsForUser(user.id, 0, CACHE_LIMIT)
                postFeedCache.set(user.id, posts)
            }
        } while (users.isNotEmpty())
    }

}
