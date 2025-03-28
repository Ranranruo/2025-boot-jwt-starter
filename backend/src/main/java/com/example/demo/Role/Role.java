package com.example.demo.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {
    @Id
    private Short id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
