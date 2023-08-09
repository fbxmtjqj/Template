package com.fbxmtjqj.template.common.exception

import org.springframework.http.HttpStatus

interface Error {

    fun type(): String
    fun key(): String
    fun httpStatus(): HttpStatus
    fun tag(): String

}
