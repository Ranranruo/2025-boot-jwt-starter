package com.example.demo;

import com.example.demo.Auth.Member.MemberDetails;
import com.example.demo.Domain.Member.MemberRepository;
import com.example.demo.Domain.Role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    @GetMapping("/")
    public String test() {
        return "test";
    }

    @GetMapping("/test")
    public MemberDetails test1(@AuthenticationPrincipal MemberDetails memberDetails) {
        return (MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/asd")
    public ResponseEntity<String> asd() {
        return ResponseEntity.ok("asd");
    }

}
