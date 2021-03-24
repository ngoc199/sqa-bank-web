package com.banking.banking.repositories;

import java.util.Optional;

import com.banking.banking.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    
    public Optional<User> findByUsername(String username);
}
