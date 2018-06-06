package com.fbw.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration()
@EnableTransactionManagement
@EnableEurekaClient
@MapperScan("com.fbw.service.mappers")
public class FbwServiceUserApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(FbwServiceUserApplication.class, args);
    }
}
