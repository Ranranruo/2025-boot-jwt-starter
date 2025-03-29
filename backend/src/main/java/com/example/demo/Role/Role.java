package com.example.demo.Role;

import com.example.demo.Bridge.MemberRoleBridge;
import com.example.demo.Member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Role")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(nullable = false, length = 30)
    private String name;

    private String description;

    @Column(nullable = false)
    private Boolean flag = false;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties("role")
    private Set<MemberRoleBridge> members = new HashSet<>();
}
