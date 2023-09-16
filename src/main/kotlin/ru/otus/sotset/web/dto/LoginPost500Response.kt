package ru.otus.sotset.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

data class LoginPost500Response(

    @Schema(example = "null", required = true, description = "Описание ошибки")
    @get:JsonProperty("message", required = true)
    val message: String,

    @Schema(example = "null", description = "Идентификатор запроса. Предназначен для более быстрого поиска проблем.")
    @get:JsonProperty("request_id")
    val requestId: String? = null,

    @Schema(
        example = "null",
        description = "Код ошибки. Предназначен для классификации проблем и более быстрого решения проблем."
    )
    @get:JsonProperty("code")
    val code: Int? = null
)