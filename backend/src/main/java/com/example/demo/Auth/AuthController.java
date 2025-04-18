package com.example.demo.Auth;

import com.example.demo.Auth.DTO.SignUpRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        System.out.println("username: "+ signUpRequestDTO.getUsername());
        System.out.println("displayname: "+ signUpRequestDTO.getDisplayName());
        System.out.println("email: "+ signUpRequestDTO.getEmail());
        System.out.println("password: "+ signUpRequestDTO.getPassword());
        return null;
//        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
