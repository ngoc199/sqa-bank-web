package com.banking.banking.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.banking.banking.models.values.AccountType;
import com.banking.banking.models.values.Money;

@Entity
@Table(name = "bank_accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class BankAccount {
    @Id
    private String id;

    @NotNull
    @Embedded
    private Money balance;

    @NotNull
    private LocalDateTime createAt;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @PrePersist
    private void onCreate() {
        id = UUID.randomUUID().toString();
        createAt = LocalDateTime.now();
    }
}
