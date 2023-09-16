package ru.otus.sotset.web.dto

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

data class PageRequest(

    @Parameter(schema = Schema(defaultValue = "0"))
    @Min(0)
    val offset: Int = 0,

    @Parameter(schema = Schema(defaultValue = "10"))
    @Min(1)
    val limit: Int = 10
)
