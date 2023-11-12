package ru.otus.sotset.model

import java.time.LocalDateTime
import java.util.UUID

data class Post(
    val id: UUID,
    val text: String,
    val authorId: UUID,
    val createdAt: LocalDateTime
)
