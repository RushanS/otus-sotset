package ru.otus.sotset.web.controller

import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.otus.sotset.web.dto.User
import ru.otus.sotset.web.dto.UserRegisterPost200Response
import ru.otus.sotset.web.dto.UserRegisterPostRequest

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class UserApiController {

    @GetMapping("/user/get/{id}")
    fun userGetIdGet(
        @Parameter(description = "Идентификатор пользователя", required = true) @PathVariable("id") id: String
    ): ResponseEntity<User> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PostMapping("/user/register")
    fun userRegisterPost(
        @Parameter @Valid @RequestBody(required = false) userRegisterRequest: UserRegisterPostRequest?
    ): ResponseEntity<UserRegisterPost200Response> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @GetMapping("/user/search")
    fun userSearchGet(
        @NotNull @Parameter(description = "Условие поиска по имени", required = true)
        @Valid @RequestParam(value = "first_name", required = true)
        firstName: String,
        @NotNull @Parameter(description = "Условие поиска по фамилии", required = true)
        @Valid @RequestParam(value = "last_name", required = true)
        lastName: String
    ): ResponseEntity<List<User>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
