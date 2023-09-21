package ru.otus.sotset.web.dto

import io.swagger.v3.oas.annotations.media.Schema

data class LoginRequest(

    @Schema(example = "null", description = "Идентификатор пользователя")
    val id: String,

    @Schema(example = "Секретная строка")
    val password: String
)
