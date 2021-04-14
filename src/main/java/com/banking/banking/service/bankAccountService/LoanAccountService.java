package com.banking.banking.service.bankAccountService;

import java.time.LocalDate;
import java.util.List;

import com.banking.banking.model.bankaccount.loanAccount.LoanAccount;
import com.banking.banking.repository.bankAccountRepo.LoanAccountRepository;
import static com.banking.banking.repository.bankAccountRepo.specification.LoanAccountSpecification.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
     * @param end
     * @param begin
     * @param ownerName
     * @param page
     * @param size
     * @return loanAccountPage
     */
    public Page<LoanAccount> getLoanAccountList(String ownerName, LocalDate begin, LocalDate end, int page, int size) {
        return loanAccountRepository.findAll(
                Specification.where(Specification.where(firstNameContains(ownerName)).or(middleNameContains(ownerName))
                        .or(lastNameContains(ownerName))).and(beforeDate(end)).and(afterDate(begin)),
                PageRequest.of(page, size));
    }
}
