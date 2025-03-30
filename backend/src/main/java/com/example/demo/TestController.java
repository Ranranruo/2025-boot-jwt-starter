package com.example.demo;

import com.example.demo.Member.Member;
import com.example.demo.Member.MemberRepository;
import com.example.demo.Role.Role;
import com.example.demo.Role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
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
    public Member test1() {
        Optional<Member> member = memberRepository.findById(1L);
        if(member.isEmpty()) {
            return null;
        }
        return member.get();
    }

    @GetMapping("/asd")
    public ResponseEntity<String> asd() {
        return ResponseEntity.ok("asd");
    }
}
