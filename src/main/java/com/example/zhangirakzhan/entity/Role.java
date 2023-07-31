package com.example.zhangirakzhan.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    SELLER,
    USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
