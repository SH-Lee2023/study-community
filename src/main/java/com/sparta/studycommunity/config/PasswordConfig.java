package com.sparta.studycommunity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() { // 맨 앞자리가 소문자로 바뀌면서 passwordEncoder로 bean으로 등록됨
        return new BCryptPasswordEncoder(); // Hash 매커니즘 가지고 passowrd를 Encode
    }
}