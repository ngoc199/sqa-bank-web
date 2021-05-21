package com.banking.banking.service.bankAccountService;

import java.time.LocalDate;
import java.util.List;

import com.banking.banking.model.bankaccount.savingsAccount.SavingsAccount;
import com.banking.banking.repository.bankAccountRepo.SavingAccountRepository;
import static com.banking.banking.repository.bankAccountRepo.specification.SavingAccountSpecification.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
     * @return savingsAccount
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
     * Get the savings accounts by the filter value (ownerName, begin date, end
     * date)
     *
     * @param ownerName
     * @param begin
     * @param end
     * @param page
     * @param size
     * @return savingsAccountsList
     */
    public Page<SavingsAccount> getSavingsAccountList(String ownerName, LocalDate begin, LocalDate end, int page,
            int size) {
        return savingAccountRepository.findAll(
                Specification.where(Specification.where(firstNameContains(ownerName)).or(middleNameContains(ownerName))
                        .or(lastNameContains(ownerName))).and(afterDate(begin)).and(beforeDate(end)),
                PageRequest.of(page, size));
    }

    /**
     * Get the list of savings account satisfy the conditions
     * @param ownerName
     * @param begin
     * @param end
     * @return savingsAccountsList
     */
    public List<SavingsAccount> getSavingsAccountList(String ownerName, LocalDate begin, LocalDate end) {
        return savingAccountRepository
                .findAll(
                        Specification
                                .where(Specification.where(firstNameContains(ownerName))
                                        .or(middleNameContains(ownerName)).or(lastNameContains(ownerName)))
                                .and(afterDate(begin)).and(beforeDate(end)));
    }
}
