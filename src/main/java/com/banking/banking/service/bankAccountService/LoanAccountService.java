package com.banking.banking.service.bankAccountService;

import java.util.List;

import com.banking.banking.model.bankaccount.loanAccount.LoanAccount;
import com.banking.banking.repository.bankAccountRepo.LoanAccountRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanAccountService {

    private final LoanAccountRepository loanAccountRepository;

    public List<LoanAccount> getLoanAccountsByCustomerId(String customerId) {
        return loanAccountRepository.findByCustomer_Id(customerId);
    }

    /**
     * Get the loan account by id
     *
     * @param accountId
     * @return loanAccount
     */
    public LoanAccount getLoanAccountById(String accountId) {
        return loanAccountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Get the list of loan accounts by page
     *
     * @param page
     * @param size
     * @return loanAccountList
     */
    public List<LoanAccount> getLoanAccountList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return loanAccountRepository.findAll(pageable).toList();
    }
}
