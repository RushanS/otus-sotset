package ru.otus.sotset.config

import com.zaxxer.hikari.HikariDataSource
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

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
