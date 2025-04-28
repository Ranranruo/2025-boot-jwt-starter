package com.example.demo.Auth.Security;

import com.example.demo.Auth.DTO.SignInRequestDTO;
import com.example.demo.Auth.DTO.SignInResponseDTO;
import com.example.demo.Auth.DTO.SignUpRequestDTO;
import com.example.demo.Auth.DTO.SignUpResponseDTO;
import com.example.demo.Common.Response.ValidationStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthValidator {
    private final int MIN_USERNAME_LENGTH = 5;
    private final int MAX_USERNAME_LENGTH = 20;
    private final int MIN_DISPLAY_NAME_LENGTH = 2;
    private final int MAX_DISPLAY_NAME_LENGTH = 16;
    private final int MIN_PASSWORD_LENGTH = 8;
    private final int MAX_PASSWORD_LENGTH = 16;

    private final String USERNAME_REGEX = "^[a-zA-Z0-9]{5,20}$";
    private final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{8,16}$";
    private final String DISPLAY_NAME_REGEX = "^[a-zA-Z0-9가-힣]{2,16}$";
    private final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    private ValidationStatus validateUsername(String username) {
        if(username.isBlank()) return ValidationStatus.EMPTY;
        else if(username.length() < this.MIN_USERNAME_LENGTH) return ValidationStatus.TOO_SHORT;
        else if(username.length() > this.MAX_USERNAME_LENGTH) return ValidationStatus.TOO_LONG;
        else if (!username.matches(this.USERNAME_REGEX)) return ValidationStatus.INVALID;
        return ValidationStatus.SUCCESS;
    }

    private ValidationStatus validateDisplayName(String displayName) {
        if(displayName.isBlank()) return ValidationStatus.EMPTY;
        else if(displayName.length() < this.MIN_DISPLAY_NAME_LENGTH) return ValidationStatus.TOO_SHORT;
        else if(displayName.length() > this.MAX_DISPLAY_NAME_LENGTH) return ValidationStatus.TOO_LONG;
        else if (!displayName.matches(this.DISPLAY_NAME_REGEX)) return ValidationStatus.INVALID;
        return ValidationStatus.SUCCESS;
    }

    private ValidationStatus validatePassword(String password) {
        if(password.isBlank()) return ValidationStatus.EMPTY;
        else if(password.length() < this.MIN_PASSWORD_LENGTH) return ValidationStatus.TOO_SHORT;
        else if(password.length() > this.MAX_PASSWORD_LENGTH) return ValidationStatus.TOO_LONG;
        else if (!password.matches(this.PASSWORD_REGEX)) return ValidationStatus.INVALID;
        return ValidationStatus.SUCCESS;
    }

    private ValidationStatus validateEmail(String email) {
        if(email != null && !email.matches(this.EMAIL_REGEX)) return ValidationStatus.INVALID;
        return ValidationStatus.SUCCESS;
    }

    public SignUpResponseDTO validateSignUpRequest(SignUpRequestDTO signUpRequestDTO) {

        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();

        String username = signUpRequestDTO.getUsername();
        String displayName = signUpRequestDTO.getDisplayName();
        String password = signUpRequestDTO.getPassword();
        String email = signUpRequestDTO.getEmail();

        signUpResponseDTO.setUsername(this.validateUsername(username));
        signUpResponseDTO.setDisplayName(this.validateDisplayName(displayName));
        signUpResponseDTO.setPassword(this.validatePassword(password));
        signUpResponseDTO.setEmail(this.validateEmail(email));

        return signUpResponseDTO;
    }

    public SignInResponseDTO validateSignInRequest(SignInRequestDTO signInRequestDTO) {
        SignInResponseDTO signInResponseDTO = new SignInResponseDTO();

        String username = signInRequestDTO.getUsername();
        String password = signInRequestDTO.getPassword();

        signInResponseDTO.setUsername(this.validateUsername(username));
        signInResponseDTO.setPassword(this.validatePassword(password));

        return signInResponseDTO;
    }
}
