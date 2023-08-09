package com.fbxmtjqj.template.common.exception

import org.springframework.http.HttpStatus

enum class JwtError(private val tag: String, private val httpStatus: HttpStatus) : Error {

    TOKEN_ERROR("TOKEN이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRATION("TOKEN이 만료 되었습니다.", HttpStatus.UNAUTHORIZED),
    ;

    override fun type(): String = "jwt"

    override fun key(): String = name

    override fun httpStatus(): HttpStatus = httpStatus

    override fun tag(): String = tag

}