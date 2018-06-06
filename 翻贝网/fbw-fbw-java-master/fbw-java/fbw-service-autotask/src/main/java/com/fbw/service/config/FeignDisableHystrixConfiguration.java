package com.fbw.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;

@Configuration
public class FeignDisableHystrixConfiguration
{

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder()
    {
        return Feign.builder();
    }
}
