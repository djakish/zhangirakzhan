package com.example.zhangirakzhan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "USERNAME", unique = true)
    @NotEmpty
    @NotNull
    private String username;

    @Column(name = "FIRST_NAME", unique = false)
    @NotEmpty
    @NotNull
    private String firstName;

    @Column(name = "LAST_NAME", unique = false)
    @NotEmpty
    @NotNull
    private String lastName;

    @Column(name = "EMAIL", unique = true)
    @Email
    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();
}
