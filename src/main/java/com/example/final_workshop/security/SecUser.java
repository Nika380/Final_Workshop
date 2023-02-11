package com.example.final_workshop.security;

import com.example.final_workshop.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class SecUser implements UserDetails {

    public SecUser(User user) {
        this.password = user.getPassword();
        this.username = user.getUsername();
        authorities = user.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName())).collect(Collectors.toList());
    }

    private String username;
    private String password;
    private Collection<SimpleGrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
