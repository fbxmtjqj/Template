package com.fbxmtjqj.template.common.config

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DotenvConfig {

    @Bean
    fun dotenv(): Dotenv = Dotenv.configure()
        .filename(".env.properties")
        .systemProperties()
        .load()
}