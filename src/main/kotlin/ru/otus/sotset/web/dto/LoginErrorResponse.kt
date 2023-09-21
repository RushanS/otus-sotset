package ru.otus.sotset.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.swagger.v3.oas.annotations.media.Schema

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class LoginErrorResponse(

    @Schema(example = "null", required = true, description = "Описание ошибки")
    val message: String,

    @Schema(example = "null", description = "Идентификатор запроса. Предназначен для более быстрого поиска проблем.")
    val requestId: String? = null,

    @Schema(
        example = "null",
        description = "Код ошибки. Предназначен для классификации проблем и более быстрого решения проблем."
    )
    val code: Int? = null
)