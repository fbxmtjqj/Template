package com.fbxmtjqj.template.common.config

import com.fbxmtjqj.template.common.interceptor.AuthenticationInterceptor
import com.fbxmtjqj.template.common.resolver.UserResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig(
    private val authenticationInterceptor: AuthenticationInterceptor,
    private val userResolver: UserResolver
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authenticationInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/swagger-resources/**")
            .excludePathPatterns("/swagger-ui/**")
            .excludePathPatterns("/swagger-ui.html")
            .excludePathPatterns("/v3/**");
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedMethods("*")
            .allowedOriginPatterns("*")
            .allowCredentials(true)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userResolver)
        super.addArgumentResolvers(resolvers)
    }
}