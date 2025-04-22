package com.example.demo.Auth;

import com.example.demo.Auth.DTO.SignUpResponseDTO;
import com.example.demo.Auth.Security.Exception.ExistsSignUpRequestException;
import com.example.demo.Auth.DTO.SignUpRequestDTO;
import com.example.demo.Domain.Member.Member;
import com.example.demo.Domain.Member.MemberRepository;
import com.example.demo.Domain.MemberRoleBridge.MemberRoleBridgeRepository;
import com.example.demo.Domain.Role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final MemberRoleBridgeRepository memberRoleBridgeRepository;

    public boolean signUp(SignUpRequestDTO signUpRequestDTO) {
        boolean isUsernameTaken = memberRepository.existsMemberByUsername(signUpRequestDTO.getUsername());
        boolean isEmailTaken = memberRepository.existsMemberByEmail(signUpRequestDTO.getEmail());

        if(isUsernameTaken || isEmailTaken) {
            SignUpResponseDTO response = new SignUpResponseDTO();
            response.setUsername("SUCCESS");

        }

//        if(isExists) throw new ExistsSignUpRequestException();
        Member member = Member.builder()
                .username(signUpRequestDTO.getUsername())
                .password(signUpRequestDTO.getPassword())
                .email(signUpRequestDTO.getEmail())
                .displayName(signUpRequestDTO.getDisplayName())
                .build();
//        Role role = roleRepository.findByName("MEMBER");
//        memberRepository.save(member);

//        Long memberId = member.getId();
//        Long roleId = role.getId();

//        MemberRoleBridge memberRoleBridge = MemberRoleBridge.builder()
//                .memberId(memberId)
//                .roleId(roleId)
//                .build();

//        memberRoleBridgeRepository.save(memberRoleBridge);
        return true;
    }
}
