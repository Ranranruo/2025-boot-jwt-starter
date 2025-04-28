package com.example.demo.Auth.Filter;

import com.example.demo.Auth.DTO.SignInRequestDTO;
import com.example.demo.Auth.DTO.SignInResponseDTO;
import com.example.demo.Auth.DTO.SignUpResponseDTO;
import com.example.demo.Auth.JWT.JWTProvider;
import com.example.demo.Auth.Member.MemberDetails;
import com.example.demo.Auth.Security.AuthValidator;
import com.example.demo.Auth.Security.Exception.NotFoundSignInException;
import com.example.demo.Common.Response.ResponseMessage;
import com.example.demo.Common.Response.ValidationStatus;
import com.example.demo.Lib.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.AuthProvider;
import java.util.Map;



public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JWTProvider jwtProvider;
    private final AuthValidator authValidator;

    public LoginFilter(AuthenticationManager authenticationManager, JWTProvider jwtProvider, AuthValidator authValidator) {
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/sign-in");

        this.jwtProvider = jwtProvider;
        this.authValidator = authValidator;
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

        SignInResponseDTO signInResponseDTO = authValidator.validateSignInRequest(signInRequestDTO);

        if(signInResponseDTO.getUsername() == ValidationStatus.ERROR || signInResponseDTO.getPassword() == ValidationStatus.ERROR) {

        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(signInRequestDTO.getUsername(), signInRequestDTO.getPassword());
        return this.getAuthenticationManager().authenticate(token);
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        System.out.println("successful authentication");
        MemberDetails member = (MemberDetails) auth.getPrincipal();

        String refreshToken = jwtProvider.generateRefreshToken(member.getUsername());
        String accessToken = jwtProvider.generateAccessToken(member.getUsername());

        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);

        response.addHeader("Authorization", "Bearer " + accessToken);

        SignInResponseDTO signInResponseDTO = new SignInResponseDTO();
        signInResponseDTO.setUsername(ValidationStatus.SUCCESS);
        signInResponseDTO.setPassword(ValidationStatus.SUCCESS);

        response.setStatus(HttpServletResponse.SC_OK);

        String json = objectMapper.writeValueAsString(new ApiResponse<SignInResponseDTO>(true, ResponseMessage.SUCCESS, signInResponseDTO));

        response.getWriter().write(json);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        System.out.println("unsuccessfulAuthentication");
        SignInResponseDTO signInResponseDTO = new SignInResponseDTO();
        signInResponseDTO.setUsername(ValidationStatus.ERROR);
        signInResponseDTO.setPassword(ValidationStatus.ERROR);
        String json = "";

        if(failed instanceof BadCredentialsException){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            json = objectMapper.writeValueAsString(new ApiResponse<SignInResponseDTO>(false, ResponseMessage.UNAUTHORIZED, signInResponseDTO));
        }

        response.getWriter().write(json);
    }
}
