package com.fbxmtjqj.template.common.resolver

import com.fbxmtjqj.template.bussiness.model.vo.UserVo
import com.fbxmtjqj.template.common.jwt.JwtComponent
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class UserResolver(
    private val jwtComponent: JwtComponent
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == UserVo::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        if (webRequest !is ServletWebRequest) throw Exception()

        return jwtComponent.getJwt(webRequest.request)
    }
}