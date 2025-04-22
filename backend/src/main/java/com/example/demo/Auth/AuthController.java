package com.example.demo.Auth;

import com.example.demo.Auth.DTO.SignUpRequestDTO;
import com.example.demo.Auth.DTO.SignUpResponseDTO;
import com.example.demo.Auth.Security.AuthValidator;
import com.example.demo.Auth.Security.Exception.InvalidSignUpRequestException;
import com.example.demo.Lib.ApiResponse;
import com.example.demo.Lib.ValidationStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthValidator authValidator;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<SignUpResponseDTO>> signUp(@Valid @RequestBody SignUpRequestDTO request) throws Exception {
        SignUpResponseDTO response = authValidator.validateSignUpRequest(request);

        boolean isValidUsername = response.getUsername().equals(ValidationStatus.SUCCESS.getMessage());
        boolean isValidDisplayName = response.getDisplayName().equals(ValidationStatus.SUCCESS.getMessage());
        boolean isValidPassword = response.getPassword().equals(ValidationStatus.SUCCESS.getMessage());
        boolean isValidEmail = response.getEmail().equals(ValidationStatus.SUCCESS.getMessage());

        if (!(isValidUsername && isValidDisplayName && isValidPassword && isValidEmail)) {
            throw new InvalidSignUpRequestException(response);
        }

        authService.signUp(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<SignUpResponseDTO>(true, "CREATED", response));
    }
}
