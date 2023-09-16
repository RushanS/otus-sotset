package ru.otus.sotset.api

import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import ru.otus.sotset.web.controller.FriendApiController

class FriendApiTest {

    private val api: FriendApiController = FriendApiController()

    /**
     * To test FriendApiController.friendDeleteUserIdPut
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun friendDeleteUserIdPutTest() {
        val userId: String = TODO()
        val response: ResponseEntity<Unit> = api.friendDeleteUserIdPut(userId)

        // TODO: test validations
    }

    /**
     * To test FriendApiController.friendSetUserIdPut
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun friendSetUserIdPutTest() {
        val userId: String = TODO()
        val response: ResponseEntity<Unit> = api.friendSetUserIdPut(userId)

        // TODO: test validations
    }
}
