package com.shoeshop.hooks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(1)
public class Broadcast implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Broadcast start to initialize ...");
    }
}
