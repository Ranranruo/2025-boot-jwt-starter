//package com.example.demo.Security;
//
//import com.example.demo.Member.Member;
//import com.example.demo.Role.Role;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//public class MemberDetails extends User {
//    private final String displayname;
//    private final String email;
//    public MemberDetails(Member member, Set<Role> roles) {
//        super(
//                member.getUsername(),
//                member.getPassword(),
//                roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toSet())
//        );
//        this.displayname = member.getUsername();
//        this.email = member.getEmail();
//    }
//}
