package ru.otus.sotset.web.controller

import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.otus.sotset.web.dto.PageRequest
import ru.otus.sotset.web.dto.Post
import ru.otus.sotset.web.dto.PostCreateRequest
import ru.otus.sotset.web.dto.PostEditRequest

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class PostApiController {

    @PostMapping("/post/create")
    fun createPost(
        @Parameter @Valid @RequestBody(required = false) createRequest: PostCreateRequest?
    ): ResponseEntity<String> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PutMapping("/post/delete/{id}")
    fun deletePost(
        @Parameter(required = true) @PathVariable("id") id: String
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @GetMapping("/post/feed")
    fun getPosts(
        @Valid pageRequest: PageRequest
    ): ResponseEntity<List<Post>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @GetMapping("/post/get/{id}")
    fun getPost(
        @Parameter(required = true) @PathVariable("id") id: String
    ): ResponseEntity<Post> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PutMapping("/post/update")
    fun editPost(
        @Parameter @Valid @RequestBody(required = false) postUpdateRequest: PostEditRequest?
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}