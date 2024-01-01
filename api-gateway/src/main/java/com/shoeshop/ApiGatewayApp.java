package com.shoeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ApiGatewayApp {

    public static void main(String[] args) {
        log.info("API Gateway To Start");
        SpringApplication.run(ApiGatewayApp.class, args);
        log.info("Started API Gateway");
    }

}
