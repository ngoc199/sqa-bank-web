package com.banking.banking.services;

import java.util.Set;

import com.banking.banking.models.SavingAccount;
import com.banking.banking.repositories.SavingAccountRepository;

import org.springframework.stereotype.Service;

@Service
public class SavingAccountService {
    
    private final SavingAccountRepository savingAccountRepo;

    public SavingAccountService(SavingAccountRepository savingAccountRepo) {
        this.savingAccountRepo = savingAccountRepo;
    }

    /**
     * <p>Find set of saving accounts by customer id</p>
     * @param customerId
     * @return savingAccountsSet
     * @author Ngoc
     */
    public Set<SavingAccount> findSavingAccountByCustomer(String customerId) {
        return savingAccountRepo.findByCustomer_Id(customerId);
    }
}
