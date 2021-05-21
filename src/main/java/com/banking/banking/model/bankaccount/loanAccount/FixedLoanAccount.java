package com.banking.banking.model.bankaccount.loanAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

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

    @Override
    public String getType() {
        return "Vay lãi suất cố định";
    }

    @Override
    public LocalDateTime getNextPayDate() {
        LocalDateTime nextPayDate = getCreatedAt().plusDays(Constants.A_MONTH);

        // If the user has paid before then calculate base on that pay date
        if (getRealPayDate() != null) {
            nextPayDate = getRealPayDate().plusDays(Constants.A_MONTH);
        }
        return nextPayDate;
    }

}
