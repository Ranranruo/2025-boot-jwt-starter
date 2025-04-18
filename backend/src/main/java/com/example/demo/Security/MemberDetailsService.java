//package com.example.demo.Security;
//
//import com.example.demo.Member.Member;
//import com.example.demo.Member.MemberRepository;
//import com.example.demo.Role.Role;
//import com.example.demo.Role.RoleRepository;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
////@NoArgsConstructor
//@Service
//@RequiredArgsConstructor
//public class MemberDetailsService implements UserDetailsService {
//    private final MemberRepository memberRepository;
//    private final RoleRepository roleRepository;
//    @Override
//    public MemberDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member member = memberRepository.findByUsername(username);
//        if(member == null) {
//            throw new UsernameNotFoundException("cannot find user " + username);
//        }
//        Set<Role> roles = roleRepository.findAllByMemberId(member.getId());
//        return new MemberDetails(member, roles);
//    }
//}
