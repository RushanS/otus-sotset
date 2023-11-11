package ru.otus.sotset.web.controller

import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.otus.sotset.service.post.PostCrudService
import ru.otus.sotset.service.post.PostFeedService
import ru.otus.sotset.web.dto.PageRequest
import ru.otus.sotset.web.dto.Post
import ru.otus.sotset.web.dto.PostCreateRequest
import ru.otus.sotset.web.dto.PostEditRequest

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class PostApiController(
    val postCrudService: PostCrudService,
    val postFeedService: PostFeedService
) {

    @PostMapping("/post/create")
    fun createPost(@Valid @RequestBody createRequest: PostCreateRequest): String {
        return postCrudService.createPost(createRequest)
    }

    @PutMapping("/post/delete/{id}")
    fun deletePost(@Parameter(required = true) @PathVariable id: String) {
        return postCrudService.deletePost(id)
    }

    @GetMapping("/post/feed")
    fun postsFeed(@Valid pageRequest: PageRequest): List<Post> {
        return postFeedService.getFeed(pageRequest)
    }

    @GetMapping("/post/get/{id}")
    fun getPost(@Parameter(required = true) @PathVariable id: String): Post {
        return postCrudService.getPost(id)
    }

    @PutMapping("/post/update")
    fun editPost(@Valid @RequestBody postEditRequest: PostEditRequest) {
        return postCrudService.editPost(postEditRequest)
    }
}
