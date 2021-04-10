package com.banking.banking.model.bankaccount.loanAccount;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mixed_loan_accounts")
public class MixedLoanAccount extends LoanAccount {

    @Override
    public void deposit(BigDecimal amount) {
        this.setBalance(this.getBalance().add(amount));

    }

}
