package ru.otus.sotset.repository.impl

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import ru.otus.sotset.model.User
import ru.otus.sotset.repository.UserRepository
import java.sql.ResultSet
import java.time.LocalDate

const val INSERT_USER = """
    INSERT INTO user(
        id,
        first_name,
        second_name,
        age,
        birthdate,
        biography,
        city,
        password
    ) VALUES (
        :id,
        :firstName,
        :secondName,
        :age,
        :birthdate,
        :biography,
        :city,
        :password
    )
"""

const val SELECT_USER = """
    SELECT
        id,
        first_name,
        second_name,
        age,
        birthdate,
        biography,
        city,
        password
    FROM user
    WHERE id = :id
"""

@Component
class UserRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : UserRepository {

    override fun create(user: User) {
        jdbcTemplate.update(INSERT_USER, user.toMap())
    }

    private fun User.toMap() = mapOf(
        "id" to id,
        "firstName" to firstName,
        "secondName" to secondName,
        "age" to age,
        "birthdate" to birthdate,
        "biography" to biography,
        "city" to city,
        "password" to password,
    )

    override fun find(id: String): User? {
        val users = jdbcTemplate.query(SELECT_USER, mapOf("id" to id), UserRowMapper)
        return if (users.isNotEmpty()) users.first() else null
    }

    object UserRowMapper : RowMapper<User> {
        override fun mapRow(rs: ResultSet, rowNum: Int): User = User(
            id = rs.getString("id"),
            firstName = rs.getString("first_name"),
            secondName = rs.getString("second_name"),
            age = rs.getInt("age"),
            birthdate = rs.getObject("birthdate", LocalDate::class.java),
            biography = rs.getString("biography"),
            city = rs.getString("city"),
            password = rs.getBytes("password")
        )
    }

}