package com.example.demo.Auth.Security;

import com.example.demo.Auth.DTO.SignUpRequestDTO;
import com.example.demo.Auth.DTO.SignUpResponseDTO;
import com.example.demo.Lib.ValidationStatus;
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

    public SignUpResponseDTO validateSignUpRequest(SignUpRequestDTO signUpRequestDTO) {

        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();

        String username = signUpRequestDTO.getUsername();
        String displayName = signUpRequestDTO.getDisplayName();
        String password = signUpRequestDTO.getPassword();
        String email = signUpRequestDTO.getEmail();


        if(username.isBlank()) {
            signUpResponseDTO.setUsername(ValidationStatus.EMPTY.getMessage());
        } else if(username.length() < this.MIN_USERNAME_LENGTH) {
            signUpResponseDTO.setUsername(ValidationStatus.TOO_SHORT.getMessage());
        } else if(username.length() > this.MAX_USERNAME_LENGTH) {
            signUpResponseDTO.setUsername(ValidationStatus.TOO_LONG.getMessage());
        } else if (!username.matches(this.USERNAME_REGEX)) {
            signUpResponseDTO.setUsername(ValidationStatus.INVALID.getMessage());
        } else {
            signUpResponseDTO.setUsername(ValidationStatus.SUCCESS.getMessage());
        }

        if(displayName.isBlank()) {
            signUpResponseDTO.setDisplayName(ValidationStatus.EMPTY.getMessage());
        } else if(displayName.length() < this.MIN_DISPLAY_NAME_LENGTH) {
            signUpResponseDTO.setDisplayName(ValidationStatus.TOO_SHORT.getMessage());
        } else if(displayName.length() > this.MAX_DISPLAY_NAME_LENGTH) {
            signUpResponseDTO.setDisplayName(ValidationStatus.TOO_LONG.getMessage());
        } else if (!displayName.matches(this.DISPLAY_NAME_REGEX)) {
            signUpResponseDTO.setDisplayName(ValidationStatus.INVALID.getMessage());
        } else {
            signUpResponseDTO.setDisplayName(ValidationStatus.SUCCESS.getMessage());
        }

        if(password.isBlank()) {
            signUpResponseDTO.setPassword(ValidationStatus.EMPTY.getMessage());
        } else if(password.length() < this.MIN_PASSWORD_LENGTH) {
            signUpResponseDTO.setPassword(ValidationStatus.TOO_SHORT.getMessage());
        } else if(password.length() > this.MAX_PASSWORD_LENGTH) {
            signUpResponseDTO.setPassword(ValidationStatus.TOO_LONG.getMessage());
        } else if (!password.matches(this.PASSWORD_REGEX)) {
            signUpResponseDTO.setPassword(ValidationStatus.INVALID.getMessage());
        } else {
            signUpResponseDTO.setPassword(ValidationStatus.SUCCESS.getMessage());
        }

        if(email != null && !email.matches(this.EMAIL_REGEX)) {
            signUpResponseDTO.setEmail(ValidationStatus.INVALID.getMessage());
        } else {
            signUpResponseDTO.setEmail(ValidationStatus.SUCCESS.getMessage());
        }


        return signUpResponseDTO;
    }
}
