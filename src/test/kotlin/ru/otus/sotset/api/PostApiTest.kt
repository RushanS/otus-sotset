package ru.otus.sotset.api

import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import ru.otus.sotset.web.controller.PostApiController
import ru.otus.sotset.web.dto.PageRequest
import ru.otus.sotset.web.dto.Post
import ru.otus.sotset.web.dto.PostCreateRequest
import ru.otus.sotset.web.dto.PostEditRequest

class PostApiTest {

    private val api: PostApiController = PostApiController()

    /**
     * To test PostApiController.postCreatePost
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun postCreatePostTest() {
        val postCreateRequest: PostCreateRequest? = TODO()
        val response: ResponseEntity<String> = api.postCreatePost(postCreateRequest)

        // TODO: test validations
    }

    /**
     * To test PostApiController.postDeleteIdPut
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun postDeleteIdPutTest() {
        val id: String = TODO()
        val response: ResponseEntity<Unit> = api.postDeleteIdPut(id)

        // TODO: test validations
    }

    /**
     * To test PostApiController.postFeedGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun postFeedGetTest() {
        val offset: Int = TODO()
        val limit: Int = TODO()
        val response: ResponseEntity<List<Post>> = api.postFeedGet(PageRequest(offset, limit))

        // TODO: test validations
    }

    /**
     * To test PostApiController.postGetIdGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun postGetIdGetTest() {
        val id: String = TODO()
        val response: ResponseEntity<Post> = api.postGetIdGet(id)

        // TODO: test validations
    }

    /**
     * To test PostApiController.postUpdatePut
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun postUpdatePutTest() {
        val postEditRequest: PostEditRequest? = TODO()
        val response: ResponseEntity<Unit> = api.postUpdatePut(postEditRequest)

        // TODO: test validations
    }
}
