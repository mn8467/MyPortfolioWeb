package com.example.toyproject1_wst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Toyproject1WstApplication {

    public static void main(String[] args) {
        SpringApplication.run(Toyproject1WstApplication.class, args);
    }

}
