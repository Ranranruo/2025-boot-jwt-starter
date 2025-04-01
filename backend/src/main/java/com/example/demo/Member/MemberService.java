package com.example.demo.Member;

import com.example.demo.DTO.SignUpDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean SignUp(SignUpDTO signUpDTO) {

        boolean isExistsUsername = memberRepository.existsMemberByUsername(signUpDTO.getUsername());
        if(isExistsUsername) {
//            return
        }
        return true;
    }
}
