package com.example.demo;

import com.example.demo.Member.Member;
import com.example.demo.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final MemberRepository memberRepository;
    @GetMapping("/")
    public String test() {
        return "test";
    }

    @GetMapping("/test")
    public Member test1() {
        Optional<Member> member = memberRepository.findById(1L);
        if(member.isPresent()) {
            return member.get();
        }
        return null;
    }
}
