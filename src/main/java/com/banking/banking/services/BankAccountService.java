package com.banking.banking.services;

import java.time.LocalDateTime;
import java.util.List;

import com.banking.banking.models.BankAccount;
import com.banking.banking.repositories.BankAccountRepository;
import com.banking.banking.repositories.specifications.BankAccountSpecifications;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BankAccountService {
    
    private final BankAccountRepository bankAccountRepo;

    public BankAccountService(BankAccountRepository bankAccountRepo) {
        this.bankAccountRepo = bankAccountRepo;
    }

    /**
     * Find all bank accounts in the database in <code>page</code> of <code>size</code>
     * @param page
     * @param size
     * @return bankAccountsList
     * @author Ngoc
     */
    public List<BankAccount> findAllBankAccounts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bankAccountRepo.findAll(pageable).toList();
    }

    /**
     * Find all bank accounts which were created from <code>startDate</code> to <code>endDate</code> in <code>page</code> of <code>size</code>
     * @param startDate
     * @param endDate
     * @param page
     * @param size
     * @return bankAccountList
     * @author Ngoc
     */
    public List<BankAccount> findBankAccountByCreateDate(LocalDateTime startDate, LocalDateTime endDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (startDate == null) {
            return bankAccountRepo.findByCreateAtBefore(endDate, pageable);
        } else if (endDate == null) {
            return bankAccountRepo.findByCreateAtAfter(startDate, pageable);
        }
        return bankAccountRepo.findByCreateAtBetween(startDate, endDate, pageable);
    }

    /**
     * Find all bank accounts which have the id similar to the search query
     * @param id
     * @param page
     * @param size
     * @return bankAccountList
     * @author Ngoc
     */
    public List<BankAccount> findBankAccountById(String id, int page, int size) {

        // If the id is empty, then return all bank accounts
        if (id.isEmpty()) {
            return findAllBankAccounts(page, size);
        }
        Pageable pageable = PageRequest.of(page, size);
        return bankAccountRepo.findByIdLike(id, pageable);
    }

    /**
     * Find all the bank accounts which have the <code>id</code> similar to the search query and were created from <code>startDate</code> to <code>endDate</code>
     * @param startDate
     * @param endDate
     * @param id
     * @param page
     * @param size
     * @return bankAccountList
     * @author Ngoc
     */
    public List<BankAccount> findBankAccounts(LocalDateTime startDate, LocalDateTime endDate, String id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bankAccountRepo.findAll(BankAccountSpecifications.searchBankAccount(startDate, endDate, id), pageable).toList();
    }

    /**
     * Get the bank account by id. Throw error if the bank account does not exist
     * @param id
     * @exception exception
     * @return bankAccount
     */
    public BankAccount getBankAccount(String id) {
        return bankAccountRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
