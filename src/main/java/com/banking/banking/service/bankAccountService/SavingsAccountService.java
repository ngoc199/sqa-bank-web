package com.banking.banking.service.bankAccountService;

import java.util.List;

import com.banking.banking.model.bankaccount.savingsAccount.SavingsAccount;
import com.banking.banking.repository.bankAccountRepo.SavingAccountRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SavingsAccountService {
    private final SavingAccountRepository savingAccountRepository;

    /**
     * Find the saving accounts of the current logged in customer
     *
     * @param customerId
     * @return
     */
    public List<SavingsAccount> getSavingAccountsByCustomerId(String customerId) {
        return savingAccountRepository.findByCustomer_Id(customerId);
    }

    /**
     * Get the saving account by id
     *
     * @param accountId
     * @return savingAccount
     */
    public SavingsAccount getSavingAccountById(String accountId) {
        return savingAccountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Get the list of saving accounts by page
     *
     * @param page
     * @param size
     * @return savingAccountList
     */
    public List<SavingsAccount> getSavingAccountList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return savingAccountRepository.findAll(pageable).toList();
    }
}
