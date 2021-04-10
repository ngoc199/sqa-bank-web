package com.banking.banking.model.bankaccount.loanAccount;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fixed_loan_accounts")
public class FixedLoanAccount extends LoanAccount {

    @Override
    public void deposit(BigDecimal amount) {
        this.setBalance(this.getBalance().add(amount));

    }

}
