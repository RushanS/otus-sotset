package ru.otus.sotset

import org.springframework.http.HttpStatus

sealed class ApiException(msg: String, val code: Int) : Exception(msg)

class NotFoundException(msg: String, code: Int = HttpStatus.NOT_FOUND.value()) : ApiException(msg, code)

class LoginException(msg: String, code: Int, val requestId: String? = null) : ApiException(msg, code)

class BadUserInputException(msg: String, code: Int = HttpStatus.BAD_REQUEST.value()): ApiException(msg, code)

class AuthException: ApiException("Not authorized request", HttpStatus.UNAUTHORIZED.value())

class ForbiddenException(msg: String = "Illegal access to resource"): ApiException(msg, HttpStatus.FORBIDDEN.value())
