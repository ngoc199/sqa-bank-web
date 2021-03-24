package com.banking.banking.services;

import com.banking.banking.models.CheckingAccount;
import com.banking.banking.repositories.CheckingAccountRepository;

import org.springframework.stereotype.Service;

@Service
public class CheckingAccountService {
    
    private final CheckingAccountRepository checkingAccountRepo;

    public CheckingAccountService(CheckingAccountRepository checkingAccountRepo) {
        this.checkingAccountRepo = checkingAccountRepo;
    }

    /**
     * <p>Find the checking account of the customer in the database.</p>
     * <p>Return the <code>checkingAccount</code> if it exist, else return <code>null</code>.
     * @param customerId
     * @return checkingAccount
     * @author Ngoc
     */
    public CheckingAccount findCheckingAccountByCustomer(String customerId) {
        return checkingAccountRepo.findByCustomer_Id(customerId).orElse(null);
    }
}
