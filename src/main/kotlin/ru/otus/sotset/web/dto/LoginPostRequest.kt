package ru.otus.sotset.web.dto

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

data class LoginPostRequest(

    @Schema(example = "null", description = "Идентификатор пользователя")
    @get:JsonProperty("id")
    val id: String? = null,

    @Schema(example = "Секретная строка", description = "")
    @get:JsonProperty("password")
    val password: String? = null
)
