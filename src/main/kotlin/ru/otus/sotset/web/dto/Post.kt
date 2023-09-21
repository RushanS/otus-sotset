package ru.otus.sotset.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 * Пост пользователя
 */
data class Post(

    @Schema(example = "1d535fd6-7521-4cb1-aa6d-031be7123c4d", description = "Идентификатор поста")
    @get:JsonProperty("id")
    val id: String? = null,

    @Schema(
        example = """Lorem ipsum dolor sit amet, consectetur adipiscing elit,
        | sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        |  Lectus mauris ultrices eros in cursus turpis massa.""",
        description = "Текст поста"
    )
    @get:JsonProperty("text")
    val text: String? = null,

    @Schema(example = "null", description = "Идентификатор пользователя")
    @get:JsonProperty("author_user_id")
    val authorUserId: String? = null
)
