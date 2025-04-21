package com.example.demo.Auth.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class SignUpRequestDTO {

    private final String username;

    private final String displayName;

    private final String password;

    private final String email;
}
