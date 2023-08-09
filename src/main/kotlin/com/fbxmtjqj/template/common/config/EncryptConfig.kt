package com.fbxmtjqj.template.common.config

import io.github.cdimascio.dotenv.Dotenv
import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimplePBEConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EncryptConfig {

    @Bean
    fun jasyptStringEncryptor(dotenv: Dotenv): StringEncryptor {
        val encryptor = PooledPBEStringEncryptor()
        val config = SimplePBEConfig()
        config.password = dotenv["CRYPTO"]
        config.poolSize = 1
        encryptor.setConfig(config)
        return encryptor
    }
}