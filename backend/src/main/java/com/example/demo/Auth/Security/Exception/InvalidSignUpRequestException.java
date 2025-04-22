package com.example.demo.Auth.Security.Exception;

import com.example.demo.Auth.DTO.SignUpResponseDTO;
import lombok.Getter;

@Getter
public class InvalidSignUpRequestException extends RuntimeException {
    private final SignUpResponseDTO response;
    public InvalidSignUpRequestException(SignUpResponseDTO signUpResponseDTO) {
        super("INVALID_SIGN_UP_REQUEST");
        this.response = signUpResponseDTO;
    }
}
