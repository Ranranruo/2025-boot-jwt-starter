package com.example.demo.Auth.DTO;

import com.example.demo.Common.Response.ValidationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDTO {
    private ValidationStatus username;
    private ValidationStatus displayName;
    private ValidationStatus password;
    private ValidationStatus email;
}
