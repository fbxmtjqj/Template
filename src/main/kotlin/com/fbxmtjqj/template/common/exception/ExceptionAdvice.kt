package com.fbxmtjqj.template.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionAdvice {

    private fun toResponseEntity(code: Error): ResponseEntity<Any> {
        return ResponseEntity(mapOf("key" to code.key(), "type" to code.type(), "tag" to code.tag()), code.httpStatus())
    }

    @ExceptionHandler(value = [Exception::class])
    fun handler(e: Exception): ResponseEntity<Any> {
        e.printStackTrace()
        return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [ServiceException::class])
    fun handler(e: ServiceException): ResponseEntity<Any> {
        e.printStackTrace()
        return toResponseEntity(e.error)
    }

}
