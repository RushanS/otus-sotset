package ru.otus.sotset.web

import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.otus.sotset.ApiException
import ru.otus.sotset.LoginException
import ru.otus.sotset.web.dto.LoginErrorResponse

@ControllerAdvice
class DefaultExceptionHandler {

    @ExceptionHandler(value = [ApiException::class])
    fun onApiException(ex: ApiException, response: HttpServletResponse): Unit =
        response.sendError(ex.code, ex.message)

    @ExceptionHandler(value = [NotImplementedError::class])
    fun onNotImplemented(ex: NotImplementedError, response: HttpServletResponse): Unit =
        response.sendError(HttpStatus.NOT_IMPLEMENTED.value())

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun onConstraintViolation(ex: ConstraintViolationException, response: HttpServletResponse): Unit =
        response.sendError(
            HttpStatus.BAD_REQUEST.value(),
            ex.constraintViolations.joinToString(", ") { it.message }
        )

    @ExceptionHandler(value = [LoginException::class])
    fun onLoginException(ex: LoginException): ResponseEntity<LoginErrorResponse> {
        val response = LoginErrorResponse(
            message = ex.message ?: "Login error",
            requestId = ex.requestId,
            code = ex.code
        )
        return ResponseEntity(response, HttpStatus.UNAUTHORIZED)
    }

}
