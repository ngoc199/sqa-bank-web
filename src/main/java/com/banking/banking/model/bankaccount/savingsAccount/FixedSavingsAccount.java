package com.banking.banking.model.bankaccount.savingsAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.banking.banking.common.Constants;

@Entity
@Table(name = "fixed_savings_accounts")
public class FixedSavingsAccount extends SavingsAccount {

    @Override
    public void withdraw(BigDecimal amount) {
        // Not supported

    }

    @Override
    public void withdrawAll() {
        // TODO Auto-generated method stub

    }

    @Override
    protected BigDecimal getCurrentSavingsInterestAmount() {
        BigDecimal balance = this.getBalance();

        // Get the rate and round it down to 2 precisions (if necessary)
        BigDecimal rate = BigDecimal.valueOf(this.getRate()).setScale(2, RoundingMode.FLOOR);

        // Calculate the saved days
        LocalDateTime createdAt = this.getCreatedAt();
        LocalDateTime today = LocalDateTime.now();
        long savedDays = ChronoUnit.DAYS.between(today, createdAt);

        // Calculate the current interest amount using the formula
        // balance * rate * savedDays / a_year
        BigDecimal interestAmount = balance.multiply(rate).multiply(BigDecimal.valueOf(savedDays))
                .divide(BigDecimal.valueOf(Constants.A_YEAR).setScale(0, RoundingMode.FLOOR));

        return interestAmount;
    }

}
