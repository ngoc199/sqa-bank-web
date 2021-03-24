package com.banking.banking.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.banking.banking.models.BankAccount;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BankAccountRepository extends JpaRepository<BankAccount, String>, JpaSpecificationExecutor<BankAccount> {
    public List<BankAccount> findByCreateAtBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    public List<BankAccount> findByCreateAtAfter(LocalDateTime startDate, Pageable pageable);
    public List<BankAccount> findByCreateAtBefore(LocalDateTime endDate, Pageable pageable);
    // public List<BankAccount> findByCustomer_NameLike(String name, Pageable pageable);
    public List<BankAccount> findByIdLike(String id, Pageable pageable);
}
