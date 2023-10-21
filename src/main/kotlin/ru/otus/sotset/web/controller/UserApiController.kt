package ru.otus.sotset.web.controller

import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.otus.sotset.service.UserService
import ru.otus.sotset.web.dto.User
import ru.otus.sotset.web.dto.UserRegisterResponse
import ru.otus.sotset.web.dto.UserRegisterRequest

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class UserApiController(
    private val userService: UserService
) {

    @GetMapping("/user/get/{id}")
    fun getUserById(
        @Parameter(description = "Идентификатор пользователя", required = true)
        @PathVariable("id") id: String
    ): User {
        return userService.getUser(id)
    }

    @PostMapping("/user/register")
    fun registerUser(@Valid @RequestBody userRegisterRequest: UserRegisterRequest): UserRegisterResponse {
        return userService.registerUser(userRegisterRequest)
    }

    @GetMapping("/user/search")
    fun searchUser(
        @Parameter(description = "Условие поиска по имени", required = true)
        @RequestParam(value = "first_name", required = true)
        firstName: String,
        @Parameter(description = "Условие поиска по фамилии", required = true)
        @RequestParam(value = "last_name", required = true)
        lastName: String
    ): List<User> {
        return userService.searchUser(firstName, lastName)
    }
}
