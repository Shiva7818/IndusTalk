package com.indusTalk.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.indusTalk.Repository.UserRepository;
import com.indusTalk.models.User;

@Service
public class CustomerUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(username);
        if(user==null){
        throw new UsernameNotFoundException("user not found with email + "+username);
        }
        List<GrantedAuthority> authories = new ArrayList<>();
        // GrantedAuthority is more concerned with websites like E-Commerce where we have to define different roles as admin,user etc;
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authories);

    }
}
