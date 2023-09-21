package ru.otus.sotset.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

data class LoginResponse(

    @Schema(example = "e4d2e6b0-cde2-42c5-aac3-0b8316f21e58", description = "")
    val token: String
)