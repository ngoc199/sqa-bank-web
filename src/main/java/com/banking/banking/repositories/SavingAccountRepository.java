package com.banking.banking.repositories;

import java.util.Set;

import com.banking.banking.models.SavingAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingAccountRepository extends JpaRepository<SavingAccount, String> {
    public Set<SavingAccount> findByCustomer_Id(String customerId);
}
