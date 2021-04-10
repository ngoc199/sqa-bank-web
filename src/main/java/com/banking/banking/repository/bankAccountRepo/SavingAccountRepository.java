package com.banking.banking.repository.bankAccountRepo;

import java.util.List;

import com.banking.banking.model.bankaccount.savingsAccount.SavingsAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingsAccount, String> {

    public List<SavingsAccount> findByCustomer_Id(String customerId);
}
