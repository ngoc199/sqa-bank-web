package com.banking.banking.model.bankaccount.savingsAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "progressive_savings_accounts")
public class ProgressiveSavingsAccount extends SavingsAccount {

    @Override
    public void withdraw(BigDecimal amount) {
        // Not supported

    }

    @Override
    public void withdrawAll() {
        // Not supported

    }

    @Override
    public BigDecimal getCurrentSavingsInterestAmount() {
        BigDecimal balance = this.getBalance();

        // Get the rate and round it down to 2 precisions (if necessary)
        BigDecimal rate = BigDecimal.valueOf(this.getRate()).setScale(2, RoundingMode.FLOOR);

        // Calculate the saved days
        LocalDateTime createdAt = this.getCreatedAt();
        LocalDateTime today = LocalDateTime.now();
        long savedDays = ChronoUnit.DAYS.between(createdAt, today);

        // Calculate the total of this month
        BigDecimal q = rate.add(BigDecimal.valueOf(1));
        BigDecimal totalAmount = balance.multiply((q).pow(Math.toIntExact(savedDays)));

        // Calculate the current interest amount using the formula
        // totalAmount - balance
        BigDecimal interestAmount = totalAmount.subtract(balance);
        return interestAmount;
    }

}
