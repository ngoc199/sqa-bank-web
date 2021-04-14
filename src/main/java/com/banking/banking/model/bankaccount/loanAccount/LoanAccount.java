package com.banking.banking.model.bankaccount.loanAccount;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.banking.banking.model.bankaccount.BankAccount;
import com.banking.banking.model.bankaccount.actions.IDepositable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "loan_accounts")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class LoanAccount extends BankAccount implements IDepositable {
    private int period; // calculated by days

    private BigDecimal moneyPay;

    // Can't track the payment with this
    // Need to create a new class to track the payment
    private LocalDate nextPayDate;
    private LocalDate realPayDate;
    private LocalDate payDeadline;
    private int deadlineModifiedCounter;

}
