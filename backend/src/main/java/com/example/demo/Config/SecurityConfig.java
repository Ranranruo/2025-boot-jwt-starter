package com.example.demo.Config;

import com.example.demo.Auth.AuthService;
import com.example.demo.Auth.Filter.JWTFilter;
import com.example.demo.Auth.Filter.LoginFilter;
import com.example.demo.Auth.JWT.JWTProvider;
import com.example.demo.Auth.Member.MemberDetailsService;
import com.example.demo.Auth.Security.AuthValidator;
import com.example.demo.Common.Redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTProvider jwtProvider;
    private final AuthValidator authValidator;
    private final RedisService redisService;
    private final MemberDetailsService memberDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .logout(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth.requestMatchers("/**").permitAll())
                .addFilterAt(new LoginFilter(authenticationConfiguration.getAuthenticationManager(), this.jwtProvider, authValidator, redisService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTFilter(this.jwtProvider, this.memberDetailsService), LoginFilter.class)
                .build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
