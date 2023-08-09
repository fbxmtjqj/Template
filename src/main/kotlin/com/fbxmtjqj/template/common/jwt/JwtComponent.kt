package com.fbxmtjqj.template.common.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fbxmtjqj.template.bussiness.model.vo.UserVo
import com.fbxmtjqj.template.common.exception.JwtError
import com.fbxmtjqj.template.common.exception.ServiceException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtComponent(
    @Value("\${security.session.expire}")
    private val duration: Duration,
    @Value("\${security.secretKey}")
    private val secretKey: String,
    private val objectMapper: ObjectMapper,
) {

    fun buildJwt(userVo: Map<String, String>): String {
        val createdDate = Date()
        val expirationDate = Date(createdDate.time + duration.toMillis())
        return Jwts.builder()
            .setHeaderParam("type", "token")
            .setClaims(userVo)
            .setIssuedAt(createdDate)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun getClaims(request: HttpServletRequest): Claims? {
        if (request.cookies == null) {
            return null
        }

        val cookie = request.cookies.firstOrNull { it.name.equals("token", true) } ?: return null
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(cookie.value).body
    }

    fun getJwt(request: HttpServletRequest): UserVo {
        val claims = getClaims(request) ?: throw ServiceException(JwtError.TOKEN_ERROR)
        val dueDate =
            LocalDateTime.ofInstant(claims.get("exp", Date::class.java).toInstant(), ZoneId.systemDefault())
        if (LocalDateTime.now() > dueDate) {
            throw ServiceException(JwtError.TOKEN_EXPIRATION)
        }

        return objectMapper.convertValue(claims, UserVo::class.java)
    }

    fun setCookie(cookie: ResponseCookie.ResponseCookieBuilder): ResponseCookie {
        return cookie
            .httpOnly(true)
            .maxAge(duration)
            .sameSite("None")
            .secure(false)
            .path("/")
            .build()
    }
}