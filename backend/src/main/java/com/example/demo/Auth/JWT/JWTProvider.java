package com.example.demo.Auth.JWT;

import io.jsonwebtoken.Jwts;
import io.lettuce.core.dynamic.annotation.Value;

public class JWTProvider {
    private final String secret;
    public JWTProvider(@Value("${spring.jwt.secret}") String secret) {
        this.secret = secret;
    }
}
