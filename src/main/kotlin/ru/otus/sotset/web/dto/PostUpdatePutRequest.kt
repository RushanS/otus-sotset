package ru.otus.sotset.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

data class PostUpdatePutRequest(

    @Schema(example = "1d535fd6-7521-4cb1-aa6d-031be7123c4d", required = true, description = "Идентификатор поста")
    @get:JsonProperty("id", required = true)
    val id: String,

    @Schema(
        example = """Lorem ipsum dolor sit amet, consectetur adipiscing elit,
        | sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        |  Lectus mauris ultrices eros in cursus turpis massa.""",
        required = true, description = "Текст поста"
    )
    @get:JsonProperty("text", required = true)
    val text: String
)