package com.project.userService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // ✅ Public endpoints
                        .requestMatchers("/users/exists").permitAll()
                        .requestMatchers("/users/profile-picture/upload").permitAll()

                        // (optional) if auth service exists
                        .requestMatchers("/auth/**").permitAll()

                        // 🔥 everything else allowed (IMPORTANT for now)
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}