package com.simple.backend.math.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.simple.backend.math")
public class MathSimpleBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(MathSimpleBackendApplication.class, args);
    }
}
