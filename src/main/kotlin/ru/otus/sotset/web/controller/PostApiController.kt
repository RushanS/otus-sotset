package ru.otus.sotset.web.controller

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.otus.sotset.web.dto.PageRequest
import ru.otus.sotset.web.dto.Post
import ru.otus.sotset.web.dto.PostCreatePostRequest
import ru.otus.sotset.web.dto.PostUpdatePutRequest

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class PostApiController {

    @PostMapping("/post/create")
    fun postCreatePost(
        @Parameter @Valid @RequestBody(required = false) createRequest: PostCreatePostRequest?
    ): ResponseEntity<String> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PutMapping("/post/delete/{id}")
    fun postDeleteIdPut(
        @Parameter(required = true) @PathVariable("id") id: String
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @GetMapping("/post/feed")
    fun postFeedGet(
        @Valid pageRequest: PageRequest
    ): ResponseEntity<List<Post>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @GetMapping("/post/get/{id}")
    fun postGetIdGet(
        @Parameter(required = true) @PathVariable("id") id: String
    ): ResponseEntity<Post> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PutMapping("/post/update")
    fun postUpdatePut(
        @Parameter @Valid @RequestBody(required = false) postUpdateRequest: PostUpdatePutRequest?
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}