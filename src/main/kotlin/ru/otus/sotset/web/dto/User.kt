package ru.otus.sotset.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

data class User(

    @Schema(example = "null", description = "Идентификатор пользователя")
    @get:JsonProperty("id") val id:
    String? = null,

    @Schema(example = "Имя", description = "Имя")
    @get:JsonProperty("first_name")
    val firstName: String? = null,

    @Schema(example = "Фамилия", description = "Фамилия")
    @get:JsonProperty("second_name")
    val secondName: String? = null,

    @Schema(example = "18", description = "Возраст")
    @get:JsonProperty("age")
    val age: Int? = null,

    @field:Valid
    @Schema(example = "Wed Feb 01 03:00:00 MSK 2017", description = "Дата рождения")
    @get:JsonProperty("birthdate")
    val birthdate: java.time.LocalDate? = null,

    @Schema(example = "Хобби, интересы и т.п.", description = "Интересы")
    @get:JsonProperty("biography")
    val biography: String? = null,

    @Schema(example = "Москва", description = "Город")
    @get:JsonProperty("city")
    val city: String? = null
)