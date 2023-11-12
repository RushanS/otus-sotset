package ru.otus.sotset.service.post

import org.springframework.stereotype.Service
import ru.otus.sotset.model.Post
import ru.otus.sotset.repository.PostRepository
import ru.otus.sotset.service.auth.CurrentUser
import ru.otus.sotset.service.page
import ru.otus.sotset.service.post.cache.CACHE_LIMIT
import ru.otus.sotset.service.post.cache.PostFeedCache
import ru.otus.sotset.web.dto.PageRequest
import ru.otus.sotset.web.dto.Post as DtoPost

@Service
class PostFeedService(
    val currentUser: CurrentUser,
    val postRepository: PostRepository,
    val postCache: PostFeedCache
) {

    fun getFeed(request: PageRequest): List<DtoPost> = with(request) {
        return if (offset + limit > CACHE_LIMIT) {
            getFromDB(offset, limit).toDto()
        } else {
            getWithCache(offset, limit).toDto()
        }
    }

    private fun getFromDB(offset: Int, limit: Int): List<Post> {
        val userId = currentUser.user.id
        return postRepository.getFriendsPostsForUser(userId, offset, limit)
    }

    private fun getWithCache(offset: Int, limit: Int): List<Post> {
        val userId = currentUser.user.id
        var posts = postCache.get(userId)
        if (posts == null) {
            posts = postRepository.getFriendsPostsForUser(userId, 0, CACHE_LIMIT)
            postCache.set(userId, posts)
        }
        return posts.page(offset, limit)
    }

    private fun List<Post>.toDto() = map {
        DtoPost(
            id = it.id.toString(),
            text = it.text,
            authorUserId = it.authorId.toString()
        )
    }

}
