package ru.otus.sotset.web.controller

import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class FriendApiController {

    @PutMapping("/friend/delete/{user_id}")
    fun friendDeleteUserIdPut(
        @Parameter(required = true) @PathVariable("user_id") userId: String
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PutMapping("/friend/set/{user_id}")
    fun friendSetUserIdPut(
        @Parameter(required = true) @PathVariable("user_id") userId: String
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
