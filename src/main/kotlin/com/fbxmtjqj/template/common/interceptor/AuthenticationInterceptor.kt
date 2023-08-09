package com.fbxmtjqj.template.common.interceptor

import com.fbxmtjqj.template.common.exception.JwtError
import com.fbxmtjqj.template.common.exception.ServiceException
import com.fbxmtjqj.template.common.jwt.JwtComponent
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationInterceptor(
    private val jwtComponent: JwtComponent,
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (HttpMethod.OPTIONS.matches(request.method)) {
            return true
        }

        jwtComponent.getJwt(request) ?: throw ServiceException(JwtError.TOKEN_ERROR)
        return true
    }
}