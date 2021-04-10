package com.banking.banking.repository.customerRepo;

import java.util.Optional;

import com.banking.banking.model.user.customer.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Optional<Customer> findByUserLogin_Username(String username);
}
