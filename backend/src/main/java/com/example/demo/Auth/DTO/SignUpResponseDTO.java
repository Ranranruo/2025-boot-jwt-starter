package com.example.demo.Auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDTO {
    private String username;
    private String displayName;
    private String password;
    private String email;
}
