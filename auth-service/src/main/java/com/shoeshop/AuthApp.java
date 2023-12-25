package com.shoeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.shoeshop.config.AuthPartyProperties;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(AuthPartyProperties.class)
@EnableDiscoveryClient
public class AuthApp {
  public static void main(String[] args) {
        SpringApplication.run(AuthApp.class, args);
  }
}
