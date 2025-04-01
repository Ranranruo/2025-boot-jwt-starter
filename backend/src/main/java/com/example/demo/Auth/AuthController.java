package com.example.demo.Auth;

import com.example.demo.DTO.SignUpDTO;
import com.example.demo.Lib.ApiResponse;
import com.example.demo.Member.Member;
import com.example.demo.Member.MemberRepository;
import com.example.demo.Member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(SignUpDTO signUpDTO) {
        memberService.SignUp(signUpDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }
}
