package com.example.demo.Auth.DTO;

import com.example.demo.Common.Response.ValidationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInResponseDTO {
    private ValidationStatus username;
    private ValidationStatus password;
}
