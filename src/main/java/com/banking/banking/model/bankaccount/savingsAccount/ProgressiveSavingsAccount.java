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
        if (!isValidAccount()) {
            return BigDecimal.valueOf(0);
        }
        BigDecimal balance = this.getBalance();

        // Get the rate
        BigDecimal rate = BigDecimal.valueOf(this.getRate());

        // Calculate the saved days
        LocalDateTime createdAt = this.getCreatedAt();
        LocalDateTime today = LocalDateTime.now();
        long savedDays = ChronoUnit.DAYS.between(createdAt, today);
        if (savedDays <= 0) {
            return BigDecimal.valueOf(0);
        }

        // Calculate the total of this month
        BigDecimal q = rate.add(BigDecimal.valueOf(1));
        BigDecimal totalAmount = balance.multiply((q).pow(Math.toIntExact(savedDays)));

        // Calculate the current interest amount using the formula
        // totalAmount - balance
        BigDecimal interestAmount = totalAmount.subtract(balance).setScale(0, RoundingMode.HALF_DOWN);
        return interestAmount;
    }

    @Override
    public LocalDateTime getDepositDate() {
        return null;
    }

    @Override
    public String getType() {
        return "Tiết kiệm tích lũy";
    }

}
