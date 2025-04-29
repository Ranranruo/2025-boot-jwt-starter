package com.example.demo.Auth.Security.Exception;

import com.example.demo.Auth.DTO.SignInResponseDTO;
import com.example.demo.Common.Response.ApiException;
import com.example.demo.Common.Response.ResponseMessage;
import lombok.Getter;

@Getter
public class InvalidSignInRequestException extends RuntimeException implements ApiException {
    private final ResponseMessage responseMessage;
    private final SignInResponseDTO response;
    public InvalidSignInRequestException(SignInResponseDTO response) {
        this.responseMessage = ResponseMessage.INVALID;
        this.response = response;
    }
}
