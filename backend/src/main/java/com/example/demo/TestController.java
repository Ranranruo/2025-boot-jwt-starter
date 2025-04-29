package com.example.demo;

import com.example.demo.Domain.Member.Member;
import com.example.demo.Domain.Member.MemberRepository;
import com.example.demo.Domain.Role.Role;
import com.example.demo.Domain.Role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public ResponseEntity<Set<Role>> test1() {
        Set<Role> roles = roleRepository.findAllByMemberId(1L);

        return ResponseEntity.ok().body(roles);
    }

    @GetMapping("/asd")
    public ResponseEntity<String> asd() {
        return ResponseEntity.ok("asd");
    }
}
