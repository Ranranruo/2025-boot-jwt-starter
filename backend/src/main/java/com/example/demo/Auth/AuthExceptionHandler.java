package com.example.demo.Auth;

import com.example.demo.Auth.Exception.InvalidSignupRequestException;
import com.example.demo.Lib.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> UsernameNotFoundException(UsernameNotFoundException e) {
        return ApiResponse.error(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(InvalidSignupRequestException.class)
    public ResponseEntity<ApiResponse<Object>> InvalidSignupRequestException(InvalidSignupRequestException e) {
        return ApiResponse.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}