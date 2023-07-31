package com.example.zhangirakzhan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "USERS")
public class User implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "ROLE")
    @NotEmpty
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

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
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
