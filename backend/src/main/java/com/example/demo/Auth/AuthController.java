package com.example.demo.Auth;

import com.example.demo.Lib.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> signUp() {
        return ApiResponse.ok();
    }
}
