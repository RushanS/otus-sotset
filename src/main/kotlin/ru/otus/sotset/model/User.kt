package ru.otus.sotset.model

import java.time.LocalDate

data class User(
    val id: String,
    val firstName: String?,
    val secondName: String?,
    val age: Int?,
    val birthdate: LocalDate?,
    val biography: String?,
    val city: String?,
    val password: ByteArray
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (firstName != other.firstName) return false
        if (secondName != other.secondName) return false
        if (age != other.age) return false
        if (birthdate != other.birthdate) return false
        if (biography != other.biography) return false
        if (city != other.city) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (secondName?.hashCode() ?: 0)
        result = 31 * result + (age ?: 0)
        result = 31 * result + (birthdate?.hashCode() ?: 0)
        result = 31 * result + (biography?.hashCode() ?: 0)
        result = 31 * result + (city?.hashCode() ?: 0)
        return result
    }

}