package com.example.demo.Domain.Member;

import com.example.demo.Auth.DTO.SignUpRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean save(SignUpRequestDTO signUpRequestDTO) {

        boolean isExistsUsername = memberRepository.existsMemberByUsername(signUpRequestDTO.getUsername());
        if(isExistsUsername) {
//            return
        }
        return true;
    }
}
