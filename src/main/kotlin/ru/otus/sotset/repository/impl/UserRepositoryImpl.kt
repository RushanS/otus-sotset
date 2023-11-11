package ru.otus.sotset.repository.impl

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import ru.otus.sotset.model.User
import ru.otus.sotset.repository.UserRepository
import java.sql.ResultSet
import java.time.LocalDate
import java.util.UUID

const val INSERT_USER = """
    INSERT INTO users(
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
    FROM users
    WHERE id = :id
"""

const val SELECT_USERS_BY_NAMES_LIKE = """
    SELECT
        id,
        first_name,
        second_name,
        age,
        birthdate,
        biography,
        city,
        password
    FROM users
    WHERE first_name LIKE :firstNamePart
        AND second_name LIKE :secondNamePart
"""

const val SELECT_ALL_USERS = """
    SELECT
        id,
        first_name,
        second_name,
        age,
        birthdate,
        biography,
        city,
        password
    FROM users
    ORDER BY id
    OFFSET :offset
    LIMIT :limit
"""

@Component
class UserRepositoryImpl(
    @Qualifier("masterJdbcTemplate") private val masterJdbcTemplate: NamedParameterJdbcTemplate,
    @Qualifier("slaveJdbcTemplate") private val slaveJdbcTemplate: NamedParameterJdbcTemplate
) : UserRepository {

    override fun create(user: User) {
        masterJdbcTemplate.update(INSERT_USER, user.toMap())
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

    override fun find(id: UUID): User? {
        val users = slaveJdbcTemplate.query(SELECT_USER, mapOf("id" to id), UserRowMapper)
        return if (users.isNotEmpty()) users.first() else null
    }

    override fun search(firstName: String, lastName: String): List<User> {
        return slaveJdbcTemplate.query(SELECT_USERS_BY_NAMES_LIKE,
            mapOf(
                "firstNamePart" to "$firstName%",
                "secondNamePart" to "$lastName%"
            ),
            UserRowMapper
        )
    }

    override fun findAll(offset: Int, limit: Int): List<User> {
        return slaveJdbcTemplate.query(SELECT_ALL_USERS, mapOf(
            "offset" to offset,
            "limit" to limit
        ), UserRowMapper)
    }

    object UserRowMapper : RowMapper<User> {
        override fun mapRow(rs: ResultSet, rowNum: Int): User = User(
            id = rs.getObject("id", UUID::class.java),
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
