package ru.otus.sotset.web.controller

import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.otus.sotset.web.dto.DialogMessage
import ru.otus.sotset.web.dto.DialogUserIdSendPostRequest

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class DialogApiController {

    @GetMapping("/dialog/{user_id}/list")
    fun dialogUserIdListGet(
        @Parameter(required = true)
        @PathVariable("user_id") userId: String
    ): ResponseEntity<List<DialogMessage>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PostMapping("/dialog/{user_id}/send")
    fun dialogUserIdSendPost(
        @Parameter(required = true) @PathVariable("user_id") userId: String,
        @Parameter @Valid @RequestBody(required = false) dialogUserIdSendPostRequest: DialogUserIdSendPostRequest?
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
