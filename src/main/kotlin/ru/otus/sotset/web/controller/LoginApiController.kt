package ru.otus.sotset.web.controller


import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import ru.otus.sotset.web.dto.LoginPost200Response
import ru.otus.sotset.web.dto.LoginPostRequest

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class LoginApiController {

    @PostMapping("/login")
    fun loginPost(
        @Parameter @Valid @RequestBody(required = false) loginRequest: LoginPostRequest?
    ): ResponseEntity<LoginPost200Response> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
