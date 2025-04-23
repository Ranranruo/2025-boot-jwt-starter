package com.example.demo.Auth.Security.Exception;

import com.example.demo.Auth.DTO.SignUpResponseDTO;
import com.example.demo.Common.Response.ApiException;
import com.example.demo.Common.Response.ResponseMessage;
import lombok.Getter;

@Getter
public class ExistsSignUpRequestException extends RuntimeException implements ApiException {
    private final ResponseMessage responseMessage;
    private final SignUpResponseDTO response;
    public ExistsSignUpRequestException(SignUpResponseDTO response) {
        this.responseMessage = ResponseMessage.EXISTS;
        this.response = response;
    }
}
