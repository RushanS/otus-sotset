package ru.otus.sotset.service.auth

import org.springframework.stereotype.Component
import ru.otus.sotset.LoginException
import ru.otus.sotset.repository.UserRepository
import ru.otus.sotset.service.toUUID
import ru.otus.sotset.web.dto.LoginRequest
import ru.otus.sotset.web.dto.LoginResponse

@Component
class AuthService(
    val userRepository: UserRepository,
    val passwordEncryptionService: PasswordEncryptionService,
    val jwtHelper: JwtHelper
) {

    fun login(loginRequest: LoginRequest): LoginResponse {
        val user = getUser(loginRequest.id)
        val passwordValid = passwordEncryptionService.check(loginRequest.password, user.password)
        if (!passwordValid) {
            throw LoginException("Invalid password", 124)
        }
        return LoginResponse(jwtHelper.createToken(user))
    }

    private fun getUser(id: String) = userRepository.find(id.toUUID())
        ?: throw LoginException("User with given id not found", 123)

    fun checkToken(token: String) {
        jwtHelper.verify(token)
    }

}
