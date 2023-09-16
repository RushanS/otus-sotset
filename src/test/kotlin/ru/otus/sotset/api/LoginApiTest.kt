package ru.otus.sotset.api

import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import ru.otus.sotset.web.controller.LoginApiController
import ru.otus.sotset.web.dto.LoginPost200Response
import ru.otus.sotset.web.dto.LoginPostRequest

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
        val loginPostRequest: LoginPostRequest? = TODO()
        val response: ResponseEntity<LoginPost200Response> = api.loginPost(loginPostRequest)

        // TODO: test validations
    }
}
