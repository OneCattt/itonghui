package com.fbw.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// @EnableEurekaClient
@EnableTransactionManagement
@EnableFeignClients
@MapperScan("com.fbw.service.mapper")
public class FbwServiceAutoTaskApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(FbwServiceAutoTaskApplication.class, args);
    }
}
