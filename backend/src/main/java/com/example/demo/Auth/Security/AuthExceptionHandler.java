package com.example.demo.Auth.Security;

import com.example.demo.Auth.DTO.SignInResponseDTO;
import com.example.demo.Auth.DTO.SignUpResponseDTO;
import com.example.demo.Auth.Security.Exception.ExistsSignUpRequestException;
import com.example.demo.Auth.Security.Exception.InvalidSignUpRequestException;
import com.example.demo.Auth.Security.Exception.NotFoundSignInException;
import com.example.demo.Lib.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(ExistsSignUpRequestException.class)
    public ResponseEntity<ApiResponse<SignUpResponseDTO>> UsernameNotFoundException(ExistsSignUpRequestException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<SignUpResponseDTO>(false, e.getResponseMessage(), e.getResponse()));
    }

    @ExceptionHandler(InvalidSignUpRequestException.class)
    public ResponseEntity<ApiResponse<SignUpResponseDTO>> InvalidSignupRequestException(InvalidSignUpRequestException e) {
        return ResponseEntity.badRequest().body(new ApiResponse<SignUpResponseDTO>(false, e.getResponseMessage(), e.getResponse()));
    }

    @ExceptionHandler(NotFoundSignInException.class)
    public ResponseEntity<ApiResponse<SignInResponseDTO>> handleNotFoundSignInException(NotFoundSignInException e) {
        return ResponseEntity.status(401).body(new ApiResponse<SignInResponseDTO>(false, e.getResponseMessage(), e.getResponse()));
    }
}