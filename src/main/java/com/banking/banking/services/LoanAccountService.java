package com.banking.banking.services;

import java.util.Set;

import com.banking.banking.models.LoanAccount;
import com.banking.banking.repositories.LoanAccountRepository;

import org.springframework.stereotype.Service;

@Service
public class LoanAccountService {
    
    public final LoanAccountRepository loanAccountRepo;

    public LoanAccountService(LoanAccountRepository loanAccountRepo) {
        this.loanAccountRepo = loanAccountRepo;
    }

    /**
     * <p>Find set of loan accounts by customer id</p>
     * @param customerId
     * @return loanAccountsSet
     * @author Ngoc
     */
    public Set<LoanAccount> findLoanAccountsByCustomer(String customerId) {
        return loanAccountRepo.findByCustomer_Id(customerId);
    }
}
