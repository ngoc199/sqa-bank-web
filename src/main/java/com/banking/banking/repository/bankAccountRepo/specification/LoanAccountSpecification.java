package com.banking.banking.repository.bankAccountRepo.specification;

import java.time.LocalDate;

import com.banking.banking.model.bankaccount.loanAccount.LoanAccount;

import org.springframework.data.jpa.domain.Specification;

public final class LoanAccountSpecification {

    /**
     * Get the accounts by owner's first name
     *
     * @param searchField
     * @return
     */
    public static Specification<LoanAccount> firstNameContains(String searchField) {
        return ownerNameContains("firstName", searchField);
    }

    /**
     * Get the accounts by owner's middle name
     *
     * @param searchField
     * @return
     */
    public static Specification<LoanAccount> middleNameContains(String searchField) {
        return ownerNameContains("middleName", searchField);
    }

    /**
     * Get the accounts by owner's last name
     *
     * @param searchField
     * @return
     */
    public static Specification<LoanAccount> lastNameContains(String searchField) {
        return ownerNameContains("lastName", searchField);
    }

    private static Specification<LoanAccount> ownerNameContains(String attribute, String value) {
        if (value == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(root.join("customer").get("fullname").get(attribute),
                containsLowerCase(value));
    }

    private static String containsLowerCase(String searchField) {
        return "%" + searchField.toLowerCase() + "%";
    }

    /**
     * Get the accounts which were created before the specified date
     *
     * @param end
     * @return
     */
    public static Specification<LoanAccount> beforeDate(LocalDate end) {
        if (end == null) {
            return null;
        }
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("createdAt"), end.atStartOfDay());
    }

    /**
     * Get the accounts which were created after the specified date
     *
     * @param begin
     * @return
     */
    public static Specification<LoanAccount> afterDate(LocalDate begin) {
        if (begin == null) {
            return null;
        }
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), begin.atStartOfDay());
    }
}
