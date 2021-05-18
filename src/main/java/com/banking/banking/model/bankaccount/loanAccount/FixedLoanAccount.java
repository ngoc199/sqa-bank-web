package com.banking.banking.model.bankaccount.loanAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.banking.banking.common.Constants;

@Entity
@Table(name = "fixed_loan_accounts")
public class FixedLoanAccount extends LoanAccount {

    @Override
    public void deposit(BigDecimal amount) {
        this.setBalance(this.getBalance().add(amount));

    }

    @Override
    public BigDecimal getCurrentPayingAmount() {

        if (!isValidAccount()) {
            return BigDecimal.valueOf(0);
        }

        BigDecimal balance = getBalance();
        BigDecimal rate = BigDecimal.valueOf(getRate());
        return balance.multiply(rate.divide(BigDecimal.valueOf(Constants.NUM_MONTHS_IN_YEAR), RoundingMode.HALF_UP)).setScale(0, RoundingMode.HALF_UP);
    }

}
