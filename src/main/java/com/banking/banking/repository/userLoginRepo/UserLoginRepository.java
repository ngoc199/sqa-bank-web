package com.banking.banking.repository.userLoginRepo;

import java.util.Optional;

import com.banking.banking.model.user.UserLogin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserLoginRepository
 */
@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {

    public Optional<UserLogin> findByUsername(String username);
}