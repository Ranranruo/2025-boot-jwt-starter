package com.example.demo.Auth.Security.Exception;

import com.example.demo.Auth.DTO.SignUpResponseDTO;
import lombok.Getter;

@Getter
public class ExistsSignUpRequestException extends RuntimeException{
    private final SignUpResponseDTO response;
    public ExistsSignUpRequestException(SignUpResponseDTO response) {
        super("EXISTS");
        this.response = response;
    }
}
