package ru.otus.sotset.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

data class UserRegisterPostRequest(

    @Schema(example = "Имя", description = "")
    @get:JsonProperty("first_name")
    val firstName: String? = null,

    @Schema(example = "Фамилия", description = "")
    @get:JsonProperty("second_name")
    val secondName: String? = null,

    @Schema(example = "18", description = "")
    @Deprecated(message = "")
    @get:JsonProperty("age")
    val age: Int? = null,

    @field:Valid
    @Schema(example = "Wed Feb 01 03:00:00 MSK 2017", description = "Дата рождения")
    @get:JsonProperty("birthdate")
    val birthdate: java.time.LocalDate? = null,

    @Schema(example = "Хобби, интересы и т.п.", description = "")
    @get:JsonProperty("biography")
    val biography: String? = null,

    @Schema(example = "Москва", description = "")
    @get:JsonProperty("city")
    val city: String? = null,

    @Schema(example = "Секретная строка", description = "")
    @get:JsonProperty("password")
    val password: String? = null
)