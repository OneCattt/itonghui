package com.fbw.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.fbw.service.mapper")
public class FbwServiceCommonApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(FbwServiceCommonApplication.class, args);
    }
}
