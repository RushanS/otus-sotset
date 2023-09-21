package ru.otus.sotset.web.controller


import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.otus.sotset.service.AuthService
import ru.otus.sotset.web.dto.LoginResponse
import ru.otus.sotset.web.dto.LoginRequest

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class LoginApiController(
    val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest): LoginResponse {
        return authService.login(loginRequest)
    }
}
