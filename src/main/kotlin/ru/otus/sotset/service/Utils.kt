package ru.otus.sotset.service

import ru.otus.sotset.BadUserInputException
import java.util.*
import kotlin.math.min

fun String?.toUUID(): UUID {
    return try {
        UUID.fromString(this)
    } catch (e: Exception) {
        throw BadUserInputException("Wrong UUID format provided")
    }
}

fun <T> List<T>.page(offset: Int, limit: Int): List<T> {
    if (offset >= size) {
        return emptyList()
    }
    return subList(offset, min(size, offset + limit))
}
