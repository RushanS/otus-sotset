package ru.otus.sotset.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.zaxxer.hikari.HikariDataSource
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import ru.otus.sotset.model.Post
import java.util.UUID

@Configuration
class AppConfiguration {

    @Bean("masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.master-datasource")
    fun masterDataSource(): HikariDataSource = HikariDataSource()

    @Bean("slaveDataSource")
    @ConfigurationProperties(prefix = "spring.slave-datasource")
    fun slaveDataSource(): HikariDataSource = HikariDataSource()

    @Bean("masterJdbcTemplate")
    @Primary
    fun masterJdbcTemplate() = NamedParameterJdbcTemplate(masterDataSource())

    @Bean("slaveJdbcTemplate")
    fun slaveJdbcTemplate() = NamedParameterJdbcTemplate(slaveDataSource())

    @Bean
    fun redisTemplate(cf: RedisConnectionFactory) = RedisTemplate<UUID, Post>()
        .apply {
            connectionFactory = cf
            defaultSerializer = Jackson2JsonRedisSerializer(redisObjectMapper(), Post::class.java)
        }

    private fun redisObjectMapper() = ObjectMapper().apply {
        registerKotlinModule()
        registerModule(JavaTimeModule())
    }

    @Bean
    fun apiInfo(): OpenAPI {
        return OpenAPI()
            .components(
                Components()
                    .addSecuritySchemes(
                        "bearerAuth", SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                    )
            )
    }
}
