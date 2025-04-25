package com.example.demo.Auth.Filter;

import com.example.demo.Auth.DTO.SignInRequestDTO;
import com.example.demo.Auth.Member.MemberDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public LoginFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/sign-in");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        SignInRequestDTO signInRequestDTO = new SignInRequestDTO();

        try {
            Map<String, Object> json = objectMapper.readValue(request.getInputStream(), Map.class);
            signInRequestDTO.setUsername(String.valueOf(json.get("username")));
            signInRequestDTO.setPassword(String.valueOf(json.get("password")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(signInRequestDTO.getUsername(), signInRequestDTO.getPassword());
        return this.getAuthenticationManager().authenticate(token);
    }

    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        System.out.println("successful authentication");
        MemberDetails member = (MemberDetails) auth.getPrincipal();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        System.out.println("unsuccessfulAuthentication");
    }
}
