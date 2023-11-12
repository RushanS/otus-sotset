package ru.otus.sotset.service.post

import org.springframework.stereotype.Service
import ru.otus.sotset.ForbiddenException
import ru.otus.sotset.NotFoundException
import ru.otus.sotset.model.Post
import ru.otus.sotset.repository.PostRepository
import ru.otus.sotset.service.auth.CurrentUser
import ru.otus.sotset.service.toUUID
import ru.otus.sotset.web.dto.Post as DtoPost
import ru.otus.sotset.web.dto.PostCreateRequest
import ru.otus.sotset.web.dto.PostEditRequest

@Service
class PostCrudService(
    val currentUser: CurrentUser,
    val postRepository: PostRepository,
    val postHandler: PostHandler
) {

    fun createPost(request: PostCreateRequest): String {
        val author = currentUser.user
        val post = postRepository.create(request.text, author.id)
        postHandler.added(post)
        return post.id.toString()
    }

    fun deletePost(id: String) {
        val post = findPost(id)
        checkOwner(post)
        postRepository.delete(id.toUUID())
        postHandler.deleted(post)
    }

    fun getPost(id: String): DtoPost {
        return findPost(id).toDto()
    }

    fun editPost(request: PostEditRequest) {
        val post = findPost(request.id)
        checkOwner(post)
        val edited = post.copy(text = request.text)
        postRepository.update(edited)
        postHandler.edited(edited)
    }

    private fun findPost(id: String): Post {
        val uuid = id.toUUID()
        return postRepository.getById(uuid) ?: throw NotFoundException("Post not found")
    }

    private fun Post.toDto() = DtoPost(
        id = id.toString(),
        text = text,
        authorUserId = authorId.toString()
    )

    private fun checkOwner(post: Post) {
        if (post.authorId != currentUser.user.id) {
            throw ForbiddenException()
        }
    }
}
