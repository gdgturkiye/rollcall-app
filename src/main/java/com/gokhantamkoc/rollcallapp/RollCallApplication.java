package com.gokhantamkoc.rollcallapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.gokhantamkoc.rollcallapp.repository")
public class RollCallApplication {

    public static void main(String[] args) {
        SpringApplication.run(RollCallApplication.class, args);
    }
}
