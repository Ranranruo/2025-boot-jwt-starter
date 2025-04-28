package com.example.demo.Auth.Security.Exception;

import com.example.demo.Common.Response.ApiException;
import com.example.demo.Common.Response.ResponseMessage;
import com.example.demo.Common.Response.ValidationStatus;
import com.example.demo.Auth.DTO.SignInResponseDTO;
import lombok.Getter;

@Getter
public class NotFoundSignInException extends RuntimeException implements ApiException {
    private final ResponseMessage responseMessage;
    private final SignInResponseDTO response;
    public NotFoundSignInException() {
        super();
        this.responseMessage = ResponseMessage.NOT_FOUND;

        this.response = new SignInResponseDTO();
        response.setUsername(ValidationStatus.SUCCESS);
        response.setPassword(ValidationStatus.SUCCESS);
    }
}
