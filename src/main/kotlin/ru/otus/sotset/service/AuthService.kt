package ru.otus.sotset.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.otus.sotset.LoginException
import ru.otus.sotset.repository.UserRepository
import ru.otus.sotset.web.dto.LoginRequest
import ru.otus.sotset.web.dto.LoginResponse
import java.security.SecureRandom
import java.time.LocalDateTime
import java.util.Base64
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import kotlin.random.asKotlinRandom

const val TOKEN_LENGTH = 64
const val TOKEN_TTL_MINUTES = 60L

@Component
class AuthService(
    val userRepository: UserRepository,
    val passwordEncryptionService: PasswordEncryptionService
) {

    val tokenCache = ConcurrentHashMap<String, LocalDateTime>()
    val random = SecureRandom().asKotlinRandom()

    fun login(loginRequest: LoginRequest): LoginResponse {
        val user = getUser(loginRequest.id)
        val passwordValid = passwordEncryptionService.check(loginRequest.password, user.password)
        if (!passwordValid) {
            throw LoginException("Invalid password", 124)
        }
        return LoginResponse(createToken())
    }

    private fun getUser(id: String) = userRepository.find(id.toUUID())
        ?: throw LoginException("User with given id not found", 123)


    private fun createToken(): String {
        val bytes = random.nextBytes(TOKEN_LENGTH)
        val token = Base64.getEncoder().encodeToString(bytes)
        tokenCache[token] = LocalDateTime.now()
        return token
    }

    fun checkToken(token: String): Boolean = tokenCache.containsKey(token)

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    fun cleanExpiredTokens() {
        tokenCache
            .filter { it.value.plusMinutes(TOKEN_TTL_MINUTES) < LocalDateTime.now() }
            .forEach { tokenCache.remove(it.key) }
    }

}
