package com.fbw.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration()
@MapperScan("com.fbw.service.mapper")
public class FbwServiceShopApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(FbwServiceShopApplication.class, args);
    }
}
