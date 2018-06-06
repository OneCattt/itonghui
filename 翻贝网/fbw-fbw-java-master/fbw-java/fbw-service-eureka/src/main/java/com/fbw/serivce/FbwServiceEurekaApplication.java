package com.fbw.serivce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FbwServiceEurekaApplication{

	public static void main(String[] args) {
		SpringApplication.run(FbwServiceEurekaApplication.class, args);
	}
}
