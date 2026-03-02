package com.example.todos.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
@Embeddable
public class Authority implements GrantedAuthority {

    private String authority;

    @Override
    public @Nullable String getAuthority() {
        return null;
    }

    public Authority() {
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
