package com.example.demo.Bridge;

import com.example.demo.Member.Member;
import com.example.demo.Role.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Member_Role_Bridge")
@Getter
@Setter
public class MemberRoleBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}

