package com.example.demo.Auth;

import com.example.demo.DTO.SignUpDTO;
import com.example.demo.Member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(SignUpDTO signUpDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
