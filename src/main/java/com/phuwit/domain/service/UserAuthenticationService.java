package com.phuwit.domain.service;

import com.phuwit.domain.entity.Role;
import com.phuwit.domain.entity.User;
import com.phuwit.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserAuthenticationService implements UserAccountService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOneByEmail(String username) {
        System.out.println(userRepository.findOneByEmail(username));
        return userRepository.findOneByEmail(username);
    }

/*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findOneByEmail(username);
        System.out.println(username);
        if(user == null) {
            System.out.println("Not Found!");
            throw new UsernameNotFoundException(username);
        }

        return new AccountUserDetails(user);
    }*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findOneByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }



}
