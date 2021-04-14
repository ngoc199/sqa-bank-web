package com.banking.banking.repository.bankAccountRepo.specification;

import java.time.LocalDate;

import com.banking.banking.model.bankaccount.savingsAccount.SavingsAccount;

import org.springframework.data.jpa.domain.Specification;

public final class SavingAccountSpecification {

    /**
     * Get the accounts by owner's first name
     *
     * @param searchField
     * @return
     */
    public static Specification<SavingsAccount> firstNameContains(String searchField) {
        return ownerNameContains("firstName", searchField);
    }

    /**
     * Get the accounts by owner's middle name
     * @param searchField
     * @return
     */
    public static Specification<SavingsAccount> middleNameContains(String searchField) {
        return ownerNameContains("middleName", searchField);
    }

    /**
     * Get the accounts by owner's last name
     * @param searchField
     * @return
     */
    public static Specification<SavingsAccount> lastNameContains(String searchField) {
        return ownerNameContains("lastName", searchField);
    }

    /**
     * Predicate if the bank's owner with the specified name contains the search value
     * @param attribute
     * @param value
     * @return
     */
    private static Specification<SavingsAccount> ownerNameContains(String attribute, String value) {
        if (value == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.join("customer").get("fullname").get(attribute)), containsLowerCase(value));
    }

    /**
     * Turns the search field to the lower case and SQL like format
     * @param searchField
     * @return
     */
    private static String containsLowerCase(String searchField) {
        return "%" + searchField.toLowerCase() + "%";
    }

    /**
     * Get the accounts which were created before the specified date
     *
     * @param end
     * @return
     */
    public static Specification<SavingsAccount> beforeDate(LocalDate end) {
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
    public static Specification<SavingsAccount> afterDate(LocalDate begin) {
        if (begin == null) {
            return null;
        }
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), begin.atStartOfDay());
    }
}
