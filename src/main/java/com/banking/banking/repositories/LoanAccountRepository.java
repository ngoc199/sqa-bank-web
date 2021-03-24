package com.banking.banking.repositories;

import java.util.Set;

import com.banking.banking.models.LoanAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanAccountRepository extends JpaRepository<LoanAccount, String> {
    public Set<LoanAccount> findByCustomer_Id(String customerId);
}
