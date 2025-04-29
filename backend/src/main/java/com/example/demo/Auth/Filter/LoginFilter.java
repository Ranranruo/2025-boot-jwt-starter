package com.example.demo.Auth.Filter;

import com.example.demo.Auth.DTO.SignInRequestDTO;
import com.example.demo.Auth.DTO.SignInResponseDTO;
import com.example.demo.Auth.JWT.JWTProvider;
import com.example.demo.Auth.Member.MemberDetails;
import com.example.demo.Auth.Security.AuthValidator;
import com.example.demo.Auth.Security.Exception.InvalidSignInRequestException;
import com.example.demo.Common.Response.ResponseMessage;
import com.example.demo.Common.Response.ValidationStatus;
import com.example.demo.Lib.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
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
        try {

            SignInRequestDTO signInRequestDTO = new SignInRequestDTO();
            Map<String, Object> json = null;
            json = objectMapper.readValue(request.getInputStream(), Map.class);
            signInRequestDTO.setUsername((String) json.get("username"));
            signInRequestDTO.setPassword((String) json.get("password"));

            request.setAttribute("signInJson", json);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(signInRequestDTO.getUsername(), signInRequestDTO.getPassword());
            return this.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        System.out.println("successful authentication");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        MemberDetails member = (MemberDetails) auth.getPrincipal();

        String role = member.getAuthorities().iterator().next().getAuthority();

        String refreshToken = jwtProvider.generateRefreshToken(member.getUsername());
        String accessToken = jwtProvider.generateAccessToken(member.getUsername(), role);

        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);

        response.addHeader("Authorization", "Bearer " + accessToken);

        SignInResponseDTO signInResponseDTO = new SignInResponseDTO();
        signInResponseDTO.setUsername(ValidationStatus.SUCCESS);
        signInResponseDTO.setPassword(ValidationStatus.SUCCESS);

        response.setStatus(HttpServletResponse.SC_OK);

        String responseToString = objectMapper.writeValueAsString(new ApiResponse<SignInResponseDTO>(true, ResponseMessage.SUCCESS, signInResponseDTO));

        response.getWriter().write(responseToString);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        System.out.println("unsuccessfulAuthentication");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        SignInRequestDTO signInRequestDTO = new SignInRequestDTO();
        Map<String, Object> json = (Map<String, Object>) request.getAttribute("signInJson");
        signInRequestDTO.setUsername((String) json.get("username"));
        signInRequestDTO.setPassword((String) json.get("password"));

        SignInResponseDTO signInResponseDTO = authValidator.validateSignInRequest(signInRequestDTO);

        boolean isValidUsername = signInResponseDTO.getUsername().equals(ValidationStatus.SUCCESS);
        boolean isValidPassword = signInResponseDTO.getPassword().equals(ValidationStatus.SUCCESS);

        String responseToString = "default";

        if (!(isValidUsername && isValidPassword)) {
            responseToString = objectMapper.writeValueAsString(new ApiResponse<SignInResponseDTO>(false, ResponseMessage.INVALID, signInResponseDTO));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(responseToString);
        } else {
            signInResponseDTO.setUsername(ValidationStatus.ERROR);
            signInResponseDTO.setPassword(ValidationStatus.ERROR);
            responseToString = objectMapper.writeValueAsString(new ApiResponse<SignInResponseDTO>(false, ResponseMessage.UNAUTHORIZED, signInResponseDTO));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(responseToString);
        }
    }
}
