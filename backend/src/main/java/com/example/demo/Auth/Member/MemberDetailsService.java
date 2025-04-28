package com.example.demo.Auth.Member;

import com.example.demo.Auth.DTO.SignInResponseDTO;
import com.example.demo.Auth.Security.AuthValidator;
import com.example.demo.Auth.Security.Exception.NotFoundSignInException;
import com.example.demo.Common.Response.ValidationStatus;
import com.example.demo.Domain.Member.Member;
import com.example.demo.Domain.Member.MemberRepository;
import com.example.demo.Domain.Role.Role;
import com.example.demo.Domain.Role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    @Override
    public MemberDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        Member member =  optionalMember.orElseThrow(NotFoundSignInException::new);

        Set<Role> roles = roleRepository.findAllByMemberId(member.getId());
        return new MemberDetails(member, roles);
    }
}