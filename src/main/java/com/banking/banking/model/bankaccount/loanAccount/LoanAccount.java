package com.banking.banking.model.bankaccount.loanAccount;

import java.time.LocalDate;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.banking.banking.model.bankaccount.BankAccount;
import com.banking.banking.model.bankaccount.actions.IDepositable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class LoanAccount extends BankAccount implements IDepositable {
    private int period; // calculated by days

    private Currency moneyPay;

    // Can't track the payment with this
    // Need to create a new class to track the payment
    private LocalDate nextPayDate;
    private LocalDate realPayDate;
    private LocalDate payDeadline;
    private int deadlineModifiedCounter;

}
