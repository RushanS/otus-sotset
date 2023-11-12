package ru.otus.sotset.service.post

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.otus.sotset.model.Post
import ru.otus.sotset.repository.FriendRepository
import ru.otus.sotset.service.post.cache.PostFeedCache
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.TimeUnit

@Component
class PostHandler(
    val friendRepository: FriendRepository,
    val postCache: PostFeedCache
) {

    private val addedPostQueue = ConcurrentLinkedQueue<Post>()
    private val editedPostQueue = ConcurrentLinkedQueue<Post>()
    private val deletedPostQueue = ConcurrentLinkedQueue<Post>()

    fun added(post: Post) {
        addedPostQueue.offer(post)
    }

    fun edited(post: Post) {
        editedPostQueue.offer(post)
    }

    fun deleted(post: Post) {
        deletedPostQueue.offer(post)
    }

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    fun handleAdded() = handle(addedPostQueue, PostFeedCache::add)

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    fun handleEdited() = handle(editedPostQueue, PostFeedCache::update)

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    fun handleDeleted() = handle(deletedPostQueue, PostFeedCache::delete)

    private fun handle(queue: Queue<Post>, action: PostFeedCache.(UUID, Post) -> Unit ) {
        while (queue.isNotEmpty()) {
            val post = queue.poll()
            val subscriberIds = friendRepository.getSubscribersOfUser(post.authorId)
            subscriberIds.forEach { postCache.action(it, post) }
        }
    }

}
