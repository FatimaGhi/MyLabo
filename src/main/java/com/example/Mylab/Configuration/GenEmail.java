package com.example.Mylab.Configuration;

import org.springframework.context.annotation.Bean;

import java.util.UUID;

public class GenEmail {


    @Bean
    public String generateVerificationCode() {
        return UUID.randomUUID().toString();
    }
}
