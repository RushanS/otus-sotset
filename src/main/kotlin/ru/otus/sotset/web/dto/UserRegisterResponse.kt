package ru.otus.sotset.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.swagger.v3.oas.annotations.media.Schema

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRegisterResponse(

    @Schema(example = "e4d2e6b0-cde2-42c5-aac3-0b8316f21e58", description = "")
    @get:JsonProperty("user_id")
    val userId: String? = null
)