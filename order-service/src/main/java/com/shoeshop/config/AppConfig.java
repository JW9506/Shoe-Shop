package com.shoeshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.shoeshop.util.JwtVerifier;

@Configuration
public class AppConfig {

  @Bean
  public JwtVerifier jwtVerifier() {
      return new JwtVerifier();
  }
}
