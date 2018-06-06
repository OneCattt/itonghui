package com.fbw.service.fbwservicezipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@SpringBootApplication
@EnableZipkinStreamServer
public class FbwServiceZipkinApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(FbwServiceZipkinApplication.class, args);
    }
}
