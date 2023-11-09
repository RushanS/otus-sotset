package ru.otus.sotset.service.auth

import org.springframework.stereotype.Service
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

private const val SALT_LENGTH = 16
private const val ITERATION_COUNT = 65536
private const val KEY_LENGTH = 128

@Service
class PasswordEncryptionService {

    val random = SecureRandom()
    val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")!!

    fun encrypt(password: String): ByteArray {
        val salt = randomSalt()
        return salt + calcHash(password, salt)
    }

    private fun randomSalt(): ByteArray {
        val salt = ByteArray(SALT_LENGTH)
        random.nextBytes(salt)
        return salt
    }

    fun check(rawPassword: String, encodedPassword: ByteArray): Boolean {
        val salt = encodedPassword.copyOfRange(0, SALT_LENGTH)
        val hash = encodedPassword.copyOfRange(SALT_LENGTH, encodedPassword.size)
        val testHash = calcHash(rawPassword, salt)
        return testHash.contentEquals(hash)
    }

    private fun calcHash(password: String, salt: ByteArray): ByteArray {
        val spec = PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH)
        return secretKeyFactory.generateSecret(spec).encoded
    }
}
