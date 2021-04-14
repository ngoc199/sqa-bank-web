package com.banking.banking.repository.bankAccountRepo;

import java.util.List;

import com.banking.banking.model.bankaccount.loanAccount.LoanAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanAccountRepository extends JpaRepository<LoanAccount, String>, JpaSpecificationExecutor<LoanAccount> {
    public List<LoanAccount> findByCustomer_Id(String customerId);
}
