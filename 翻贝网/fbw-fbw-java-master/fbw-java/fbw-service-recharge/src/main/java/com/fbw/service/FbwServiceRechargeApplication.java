package com.fbw.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
@EnableAutoConfiguration()
@EnableFeignClients
@MapperScan("com.fbw.service.mapper")
public class FbwServiceRechargeApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(FbwServiceRechargeApplication.class, args);
    }
}
