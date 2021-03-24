package com.banking.banking.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.banking.banking.models.values.Way;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "loan_accounts")
public class LoanAccount extends BankAccount {
    @Getter
    @Setter
    private LocalDateTime nextPayDate;

    @Getter
    @Setter
    private Way wayPay;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @Getter
    @Setter
    private Customer customer;
}
