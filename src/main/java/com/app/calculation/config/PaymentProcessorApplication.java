package com.app.calculation.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.app.calculation")
public class PaymentProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentProcessorApplication.class, args);
    }
}