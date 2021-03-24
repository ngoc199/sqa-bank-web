package com.banking.banking.repositories.specifications;

import java.time.LocalDateTime;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.banking.banking.models.BankAccount;

import org.springframework.data.jpa.domain.Specification;

public class BankAccountSpecifications {

    /**
     * Find the bank accounts by <code>startDate</code>, <code>endDate</code> and <code>id</code>
     * @param startDate
     * @param endDate
     * @param id
     * @return searchPredicate
     * @author Ngoc
     */
    public static Specification<BankAccount> searchBankAccount(LocalDateTime startDate, LocalDateTime endDate, String id) {
        return new Specification<BankAccount>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<BankAccount> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.and(builder.between(root.get("createAt"), startDate.toString(), endDate.toString()),
                        builder.like(root.get("id"), "%" + id + "%"));
            }

        };
    }

}
