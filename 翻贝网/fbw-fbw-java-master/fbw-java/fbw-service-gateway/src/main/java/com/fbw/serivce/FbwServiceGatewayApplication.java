package com.fbw.serivce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import com.fbw.serivce.filters.PreRequestFiller;


@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class FbwServiceGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(FbwServiceGatewayApplication.class, args);
	}
	
	@Bean
	public PreRequestFiller preRequestFiller() {
		return new PreRequestFiller();
	}
}
