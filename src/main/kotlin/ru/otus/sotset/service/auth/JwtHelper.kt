package ru.otus.sotset.service.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.otus.sotset.AuthException
import ru.otus.sotset.model.User
import ru.otus.sotset.service.toUUID
import java.time.Instant
import java.util.*

private const val USER_ID_CLAIM = "user_id"

@Component
class JwtHelper {

    @Value("\${otus-sotset.auth.jwt.secret}")
    private lateinit var secret: String

    @Value("\${otus-sotset.auth.jwt.issuer}")
    private lateinit var issuer: String

    @Value("\${otus-sotset.auth.jwt.token-ttl-seconds}")
    private var ttlSeconds: Long = 3600

    private lateinit var algorithm: Algorithm

    private lateinit var verifier: JWTVerifier

    @PostConstruct
    fun init() {
        algorithm = Algorithm.HMAC256(secret)
        verifier = JWT.require(algorithm)
            .withIssuer(issuer)
            .build()
    }

    fun createToken(user: User): String = JWT.create()
        .withIssuer(issuer)
        .withExpiresAt(Instant.now().plusSeconds(ttlSeconds))
        .withClaim(USER_ID_CLAIM, user.id.toString())
        .sign(algorithm)

    fun getUserIdFromToken(token: String): UUID = verify(token)
        .getClaim(USER_ID_CLAIM)
        .asString()
        .toUUID()

    fun verify(token: String): DecodedJWT =
        try {
            verifier.verify(token)
        } catch (ex: Exception) {
            throw AuthException()
        }

}
