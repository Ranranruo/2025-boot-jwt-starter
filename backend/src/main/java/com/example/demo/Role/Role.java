package com.example.demo.Role;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
