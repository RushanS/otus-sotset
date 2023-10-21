package ru.otus.sotset.service

import org.springframework.stereotype.Service
import ru.otus.sotset.NotFoundException
import ru.otus.sotset.repository.UserRepository
import ru.otus.sotset.web.dto.User
import ru.otus.sotset.web.dto.UserRegisterResponse
import ru.otus.sotset.web.dto.UserRegisterRequest
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncryptionService: PasswordEncryptionService
) {

    fun registerUser(request: UserRegisterRequest): UserRegisterResponse {
        val user = request.getUserModel()
        userRepository.create(user)
        return UserRegisterResponse(user.id)
    }

    fun getUser(id: String): User {
        return userRepository.find(id)?.toDto()
            ?: throw NotFoundException("User with given id not found")
    }

    fun searchUser(firstName: String, lastName: String): List<User> {
        return userRepository.search(firstName, lastName)
            .sortedBy { it.id }
            .map { it.toDto() }
    }

    private fun UserRegisterRequest.getUserModel(): ru.otus.sotset.model.User =
        ru.otus.sotset.model.User(
            id = UUID.randomUUID().toString(),
            firstName = this.firstName,
            secondName = this.secondName,
            age = this.age,
            birthdate = this.birthdate,
            biography = this.biography,
            city = this.city,
            password = passwordEncryptionService.encrypt(this.password)
        )

    private fun ru.otus.sotset.model.User.toDto() =
        User(
            id = id,
            firstName = firstName,
            secondName = secondName,
            age = age,
            birthdate = birthdate,
            biography = biography,
            city = city
        )

}
