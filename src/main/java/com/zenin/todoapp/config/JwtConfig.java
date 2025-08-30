package com.zenin.todoapp.config;

import com.zenin.todoapp.service.auth.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtConfig {
    @Bean
    JwtDecoder jwtDecoder(TokenService tokenService) {
        return NimbusJwtDecoder.withSecretKey(tokenService.getKey()).build();
    }
}