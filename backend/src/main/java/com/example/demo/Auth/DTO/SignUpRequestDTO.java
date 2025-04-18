package com.example.demo.Auth.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class SignUpRequestDTO {

    @NotBlank(message = "EMPTY_USERNAME")
    @Size(min = 5, max = 20, message = "INVALID_USERNAME_LENGTH")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "INVALID_USERNAME")
    private final String username;

    @NotBlank(message = "EMPTY_PASSWORD")
    @Size(min = 8, max = 16, message = "INVALID_PASSWORD_LENGTH")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{8,16}$", message = "INVALID_PASSWORD")
    private final String password;

    @NotBlank(message = "EMPTY_USERNAME")
    @Size(min = 2, max = 16, message = "INVALID_DISPLAY_NAME_LENGTH")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,16}$", message = "INVALID_DISPLAY_NAME")
    private final String displayName;

    @Email(message = "INVALID_EMAIL")
    private final String email;


}
