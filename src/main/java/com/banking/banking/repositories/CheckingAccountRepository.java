package com.banking.banking.repositories;

import java.util.Optional;

import com.banking.banking.models.CheckingAccount;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CheckingAccountRepository
 */
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, String> {

    public Optional<CheckingAccount> findByCustomer_Id(String customerId);
}