package ru.otus.sotset.service.auth

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST
import ru.otus.sotset.model.User
import ru.otus.sotset.repository.UserRepository

@Component
@Scope(SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
class CurrentUser(
    val request: HttpServletRequest,
    val jwtHelper: JwtHelper,
    val userRepository: UserRepository
) {

    val user: User by lazy { getUserFromRequest() }

    private fun getUserFromRequest(): User {
        val header = request.getHeader("Authorization")
        val token = header.substringAfter("Bearer ")
        val userId = jwtHelper.getUserIdFromToken(token)
        return userRepository.find(userId)
            ?: throw IllegalStateException("Cannot find user with id from auth token")
    }

}
