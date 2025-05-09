package com.example.demo.Auth;

import com.example.demo.Auth.DTO.SignUpResponseDTO;
import com.example.demo.Auth.DTO.SignUpRequestDTO;
import com.example.demo.Auth.Member.MemberDetails;
import com.example.demo.Auth.Security.Exception.ExistsSignUpRequestException;
import com.example.demo.Domain.Member.Member;
import com.example.demo.Domain.Member.MemberRepository;
import com.example.demo.Domain.MemberRoleBridge.MemberRoleBridge;
import com.example.demo.Domain.MemberRoleBridge.MemberRoleBridgeRepository;
import com.example.demo.Domain.Role.Role;
import com.example.demo.Domain.Role.RoleRepository;
import com.example.demo.Common.Response.ValidationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final MemberRoleBridgeRepository memberRoleBridgeRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public boolean signUp(SignUpRequestDTO signUpRequestDTO) {
        boolean isUsernameTaken = memberRepository.existsMemberByUsername(signUpRequestDTO.getUsername());
        boolean isEmailTaken = memberRepository.existsMemberByEmail(signUpRequestDTO.getEmail());

        if(isUsernameTaken || isEmailTaken) {
            SignUpResponseDTO response = new SignUpResponseDTO();
            response.setUsername(isUsernameTaken ? ValidationStatus.EXISTS : ValidationStatus.SUCCESS);
            response.setEmail(isEmailTaken ? ValidationStatus.EXISTS : ValidationStatus.SUCCESS);
            response.setDisplayName(ValidationStatus.SUCCESS);
            response.setPassword(ValidationStatus.SUCCESS);

            throw new ExistsSignUpRequestException(response);
        }

        String password = passwordEncoder.encode(signUpRequestDTO.getPassword());

        Member member = Member.builder()
                .username(signUpRequestDTO.getUsername())
                .password(password)
                .email(null)
                .displayName(signUpRequestDTO.getDisplayName())
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
