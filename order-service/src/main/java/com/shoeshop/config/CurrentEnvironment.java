package com.shoeshop.config;

import java.util.Arrays;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CurrentEnvironment {

    private final Environment environment;

    public boolean isDev() {
        return Arrays.stream(environment.getActiveProfiles()).noneMatch((p) -> "prod".equalsIgnoreCase(p));
    }
}
