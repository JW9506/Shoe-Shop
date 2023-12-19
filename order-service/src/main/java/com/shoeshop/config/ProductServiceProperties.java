package com.shoeshop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "microservice.product-service")
public class ProductServiceProperties {
    private String url;
    private String allProducts;
}

