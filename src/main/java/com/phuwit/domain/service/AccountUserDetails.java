package com.phuwit.domain.service;

import com.phuwit.domain.entity.Role;
import com.phuwit.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AccountUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private User user;

    public AccountUserDetails(User user) {
        this.user = user;
        System.out.println(this.user.getEmail());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        Set<Role> userRoles = user.getRoles();
        for( Role role : userRoles ) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }


    // Get registered password
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }


    // judge if account is within expiration date
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // judge if account is locked or not
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // judge credentials are within expiration date
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    // judge if account is enabled or not
    @Override
    public boolean isEnabled() {
        return true;
    }





}
