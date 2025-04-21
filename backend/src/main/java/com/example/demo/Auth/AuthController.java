package com.example.demo.Auth;

import com.example.demo.Auth.DTO.SignUpRequestDTO;
import com.example.demo.Auth.DTO.SignUpResponseDTO;
import com.example.demo.Auth.Security.AuthValidator;
import com.example.demo.Auth.Security.Exception.InvalidSignUpRequestException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity signUp(@Valid @RequestBody SignUpRequestDTO request) throws Exception {
        SignUpResponseDTO response = authValidator.validateSignUpRequest(request);
        if(!response.isSuccess()) throw new InvalidSignUpRequestException(response);
//        authService.signUp()
        return null;
    }
}
