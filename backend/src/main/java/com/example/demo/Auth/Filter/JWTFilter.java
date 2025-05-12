package com.example.demo.Auth.Filter;

import com.example.demo.Auth.AuthService;
import com.example.demo.Auth.JWT.JWTProvider;
import com.example.demo.Auth.Member.MemberDetails;
import com.example.demo.Auth.Member.MemberDetailsService;
import com.example.demo.Common.Response.ResponseMessage;
import com.example.demo.Domain.Member.MemberRepository;
import com.example.demo.Lib.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

public class JWTFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JWTProvider jwtProvider;
    private final MemberDetailsService memberDetailsService;

    public JWTFilter(JWTProvider jwtProvider, MemberDetailsService memberDetailsService) {
        this.jwtProvider = jwtProvider;
        this.memberDetailsService = memberDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String accessToken = authorization.substring("Bearer ".length());

        try {
            jwtProvider.isExpired(accessToken);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            objectMapper.writeValue(response.getWriter(), new ApiResponse<String>(false, ResponseMessage.UNAUTHORIZED, null));
            return;
        }

        String username = jwtProvider.getUsername(accessToken);
        UserDetails userDetails = memberDetailsService.loadUserByUsername(username);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        filterChain.doFilter(request, response);
    }
}
