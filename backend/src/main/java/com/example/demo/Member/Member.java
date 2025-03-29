package com.example.demo.Member;

import com.example.demo.Bridge.MemberRoleBridge;
import com.example.demo.Role.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Member")
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "displayname")
    private String displayname;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "member")
    @JsonIgnoreProperties("member")
    private Set<MemberRoleBridge> roles = new HashSet<>();
}
