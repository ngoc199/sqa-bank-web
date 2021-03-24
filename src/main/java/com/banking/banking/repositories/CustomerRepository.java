package com.banking.banking.repositories;

import java.util.Optional;

import com.banking.banking.models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    public Optional<Customer> findByUsername(String username);
}
