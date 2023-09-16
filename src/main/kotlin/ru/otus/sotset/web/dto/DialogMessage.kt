package ru.otus.sotset.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

data class DialogMessage(

    @Schema(example = "null", required = true, description = "Идентификатор пользователя")
    @get:JsonProperty("from", required = true)
    val from: String,

    @Schema(example = "null", required = true, description = "Идентификатор пользователя")
    @get:JsonProperty("to", required = true)
    val to: String,

    @Schema(example = "Привет, как дела?", required = true, description = "Текст сообщения")
    @get:JsonProperty("text", required = true)
    val text: String
)
