package com.example.demo.Auth.Security;

import com.example.demo.Auth.DTO.SignUpResponseDTO;
import com.example.demo.Auth.Security.Exception.InvalidSignUpRequestException;
import com.example.demo.Lib.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> UsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<Object>(false, e.getMessage(), null));
    }

    @ExceptionHandler(InvalidSignUpRequestException.class)
    public ResponseEntity<ApiResponse<SignUpResponseDTO>> InvalidSignupRequestException(InvalidSignUpRequestException e) {
        return ResponseEntity.badRequest().body(new ApiResponse<SignUpResponseDTO>(false, e.getMessage(), e.getSignUpResponseDTO()));
    }
}