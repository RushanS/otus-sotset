package ru.otus.sotset.api

import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import ru.otus.sotset.web.controller.LoginApiController
import ru.otus.sotset.web.dto.LoginResponse
import ru.otus.sotset.web.dto.LoginRequest

class LoginApiTest {

    private val api: LoginApiController = LoginApiController()

    /**
     * To test LoginApiController.loginPost
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun loginPostTest() {
        val loginRequest: LoginRequest? = TODO()
        val response: ResponseEntity<LoginResponse> = api.loginPost(loginRequest)

        // TODO: test validations
    }
}
