package ru.otus.sotset.api

import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import ru.otus.sotset.web.controller.UserApiController
import ru.otus.sotset.web.dto.User
import ru.otus.sotset.web.dto.UserRegisterResponse
import ru.otus.sotset.web.dto.UserRegisterRequest

class UserApiTest {

    private val api: UserApiController = UserApiController()

    /**
     * To test UserApiController.userGetIdGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun userGetIdGetTest() {
        val id: String = TODO()
        val response: ResponseEntity<User> = api.userGetIdGet(id)

        // TODO: test validations
    }

    /**
     * To test UserApiController.userRegisterPost
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun userRegisterPostTest() {
        val userRegisterRequest: UserRegisterRequest? = TODO()
        val response: ResponseEntity<UserRegisterResponse> = api.userRegisterPost(userRegisterRequest)

        // TODO: test validations
    }

    /**
     * To test UserApiController.userSearchGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun userSearchGetTest() {
        val firstName: String = TODO()
        val lastName: String = TODO()
        val response: ResponseEntity<List<User>> = api.userSearchGet(firstName, lastName)

        // TODO: test validations
    }
}
