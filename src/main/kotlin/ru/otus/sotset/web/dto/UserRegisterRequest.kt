package ru.otus.sotset.web.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRegisterRequest(

    @Schema(example = "Имя", description = "")
    val firstName: String? = null,

    @Schema(example = "Фамилия", description = "")
    val secondName: String? = null,

    @Schema(example = "18", description = "")
    val age: Int? = null,

    @field:Valid
    @Schema(example = "Wed Feb 01 03:00:00 MSK 2017", description = "Дата рождения")
    val birthdate: java.time.LocalDate? = null,

    @Schema(example = "Хобби, интересы и т.п.", description = "")
    val biography: String? = null,

    @Schema(example = "Москва", description = "")
    val city: String? = null,

    @NotBlank
    @Schema(example = "Секретная строка", description = "")
    val password: String
)