package com.example.demo.Auth;

import com.example.demo.DTO.SignUpDTO;
import com.example.demo.Member.Member;
import com.example.demo.Member.MemberRepository;
import com.example.demo.MemberRoleBridge.MemberRoleBridge;
import com.example.demo.MemberRoleBridge.MemberRoleBridgeRepository;
import com.example.demo.Role.Role;
import com.example.demo.Role.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    MemberRepository memberRepository;
    RoleRepository roleRepository;
    MemberRoleBridgeRepository memberRoleBridgeRepository;
    public boolean signUp(SignUpDTO signUpDTO) {
        Member member = Member.builder()
                .username(signUpDTO.getUsername())
                .password(signUpDTO.getPassword())
                .email(signUpDTO.getEmail())
                .displayname(signUpDTO.getDisplayname())
                .build();
        Role role = roleRepository.findByName("MEMBER");

        memberRepository.save(member);

        Long memberId = member.getId();
        Long roleId = role.getId();

        MemberRoleBridge memberRoleBridge = MemberRoleBridge.builder()
                .memberId(memberId)
                .roleId(roleId)
                .build();

        memberRoleBridgeRepository.save(memberRoleBridge);
        return true;
    }
}
