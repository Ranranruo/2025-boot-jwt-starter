package com.example.demo.Auth.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTProvider {
    private final SecretKey secret;

    public JWTProvider(@Value("${spring.jwt.secret}") String secret) {
        this.secret = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES");
    }

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(this.secret, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(this.secret, SignatureAlgorithm.HS256)
                .compact();
    }
}
