package ru.otus.sotset.service

import ru.otus.sotset.BadUserInputException
import java.util.*

fun String?.toUUID(): UUID {
    return try {
        UUID.fromString(this)
    } catch (e: Exception) {
        throw BadUserInputException("Wrong UUID format provided")
    }
}
