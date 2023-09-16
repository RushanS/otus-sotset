package ru.otus.sotset.api

import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import ru.otus.sotset.web.controller.DialogApiController
import ru.otus.sotset.web.dto.DialogMessage
import ru.otus.sotset.web.dto.DialogUserIdSendPostRequest

class DialogApiTest {

    private val api: DialogApiController = DialogApiController()

    /**
     * To test DialogApiController.dialogUserIdListGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun dialogUserIdListGetTest() {
        val userId: String = TODO()
        val response: ResponseEntity<List<DialogMessage>> = api.dialogUserIdListGet(userId)

        // TODO: test validations
    }

    /**
     * To test DialogApiController.dialogUserIdSendPost
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun dialogUserIdSendPostTest() {
        val userId: String = TODO()
        val dialogUserIdSendPostRequest: DialogUserIdSendPostRequest? = TODO()
        val response: ResponseEntity<Unit> = api.dialogUserIdSendPost(userId, dialogUserIdSendPostRequest)

        // TODO: test validations
    }
}
