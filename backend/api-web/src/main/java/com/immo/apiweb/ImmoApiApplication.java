package com.immo.apiweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.immo.apiweb",
        "com.immo.infrastructure",
        "com.immo.service",
        "com.immo.common"
})
public class ImmoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImmoApiApplication.class, args);
    }
}
