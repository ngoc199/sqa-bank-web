package com.banking.banking.services;

import com.banking.banking.models.User;
import com.banking.banking.repositories.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * AuthService
 */
@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepo;

    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * <p>Find the user by the username.</p>
     * <p>Return the user detail if it exists, else throw error.</p>
     * @param username
     * @return userDetails
     * @author Ngoc
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow();
        return user;
    }

    
}