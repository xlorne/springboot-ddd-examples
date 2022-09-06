package com.example.leave;

import com.example.leave.jwt.Jwt;
import com.example.leave.properties.LeaveProperties;
import com.example.leave.web.handler.ServletExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "leave.properties")
    public LeaveProperties todoProperties(){
        return new LeaveProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public Jwt jwt(LeaveProperties properties){
        return new Jwt(properties.getJwtSecretKey(),properties.getJwtTime(),properties.getJwtRestTime());
    }

    @Bean
    @ConditionalOnMissingBean
    public ServletExceptionHandler servletExceptionHandler(){
        return new ServletExceptionHandler();
    }

}
