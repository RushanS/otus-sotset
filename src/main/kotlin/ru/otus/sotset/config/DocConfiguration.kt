package ru.otus.sotset.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.security.SecurityScheme

@Configuration
class DocConfiguration {

    @Bean
    fun apiInfo(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("OTUS Highload Architect")
                    .description(
                        """No description provided (generated by Openapi Generator
                        | https://github.com/openapitools/openapi-generator)""".trimMargin()
                    )
                    .version("1.2.0")
            )
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
