package com.example.demo.Auth;

import com.example.demo.Auth.DTO.SignUpRequestDTO;
import com.example.demo.Auth.DTO.SignUpResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@Valid @RequestBody SignUpRequestDTO request) {
        SignUpResponseDTO response = new SignUpResponseDTO();

        String username = request.getUsername();
        String displayName = request.getDisplayName();
        String password = request.getPassword();
        String email = request.getEmail();

        response.setUsername(username.matches(AuthRegExp.USERNAME.getPattern()) ? "a" : "");
        authService.signUp(request);
        return null;
//        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
