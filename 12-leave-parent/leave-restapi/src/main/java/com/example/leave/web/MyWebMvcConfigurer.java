package com.example.leave.web;

import com.example.leave.jwt.Jwt;
import com.example.leave.web.interceptor.TokenAuthenticationHandlerInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    private final Jwt jwt;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenAuthenticationHandlerInterceptor(jwt)).addPathPatterns("/api/**");
    }




}
