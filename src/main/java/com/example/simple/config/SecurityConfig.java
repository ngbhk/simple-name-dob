package com.example.simple.config;

// src/main/java/com/example/simple/config/SecurityConfig.java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // dev only; remove or configure for production
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**", "/h2-console/**", "/", "/actuator/**").permitAll()
                        .anyRequest().authenticated()
                )
                // allow H2 console frames
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }
}

