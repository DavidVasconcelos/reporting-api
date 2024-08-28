package com.speedyteller.reporting.api.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.security.SecurityScheme.In
import io.swagger.v3.oas.models.security.SecurityScheme.Type
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .addSecurityItem(
                SecurityRequirement()
                    .addList("JWT Authentication")
            )
            .components(
                Components()
                    .addSecuritySchemes("JWT Authentication", createAPIKeyScheme())
            )
    }

    private fun createAPIKeyScheme(): SecurityScheme {
        return SecurityScheme().type(Type.APIKEY)
            .`in`(In.HEADER)
            .name(TOKEN_HEADER)
    }

    companion object {
        const val TOKEN_HEADER = "Authorization"
    }
}
