package com.fbxmtjqj.template.common.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@OpenAPIDefinition(
    info = Info(title = "API Docs", version = "v1", contact = Contact(name = "fbxmtjqj")))
@Component
class OpenApiConfig {

    @Bean
    fun openAPI(): OpenAPI {
        val securityScheme = SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .`in`(SecurityScheme.In.HEADER)
            .name("Authorization")
        val securityRequirement = SecurityRequirement().addList("bearerAuth")
        return OpenAPI()
            .components(Components().addSecuritySchemes("bearerAuth", securityScheme))
            .addSecurityItem(securityRequirement)
    }
}