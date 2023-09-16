package ru.otus.sotset.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

data class DialogUserIdSendPostRequest(

    @Schema(example = "Привет, как дела?", required = true, description = "Текст сообщения")
    @get:JsonProperty("text", required = true)
    val text: String
)

