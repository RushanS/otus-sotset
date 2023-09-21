package ru.otus.sotset.web.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class User(

    @Schema(example = "null", description = "Идентификатор пользователя")
    val id: String? = null,

    @Schema(example = "Имя", description = "Имя")
    val firstName: String? = null,

    @Schema(example = "Фамилия", description = "Фамилия")
    val secondName: String? = null,

    @Schema(example = "18", description = "Возраст")
    val age: Int? = null,

    @field:Valid
    @Schema(example = "Wed Feb 01 03:00:00 MSK 2017", description = "Дата рождения")
    val birthdate: java.time.LocalDate? = null,

    @Schema(example = "Хобби, интересы и т.п.", description = "Интересы")
    val biography: String? = null,

    @Schema(example = "Москва", description = "Город")
    val city: String? = null
)