package com.banking.banking.model.bankaccount.savingsAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.banking.banking.common.Constants;
import com.banking.banking.model.bankaccount.actions.IDepositable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accrual_savings_accounts")
public class AccrualSavingsAccount extends SavingsAccount implements IDepositable {

    @Getter
    @Setter
    private BigDecimal monthlyAmount;

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

        // Get the rate in month
        BigDecimal rate = BigDecimal.valueOf(this.getRate()).divide(BigDecimal.valueOf(Constants.NUM_MONTHS_IN_YEAR), RoundingMode.HALF_DOWN);

        // Calculate the saved days
        LocalDateTime createdAt = this.getCreatedAt();
        LocalDateTime today = LocalDateTime.now();
        long savedDays = ChronoUnit.DAYS.between(createdAt, today);
        if (savedDays <= 0) {
            return BigDecimal.valueOf(0);
        }

        // Calculate the total of this month
        BigDecimal q = rate.add(BigDecimal.valueOf(1));
        BigDecimal monthNum = BigDecimal.valueOf(this.getPeriod() / Constants.A_MONTH).setScale(0, RoundingMode.HALF_DOWN);
        BigDecimal totalAmount = monthlyAmount.multiply(q)
                .multiply(((q).pow(monthNum.intValue())).subtract(BigDecimal.valueOf(1))).divide(rate);

        // Calculate the current interest amount using the formula
        // (totalAmount - monthNum * monthlyAmount) / period (days) * savedDays
        BigDecimal interestAmount = totalAmount.subtract(monthNum.multiply(monthlyAmount)).divide(BigDecimal.valueOf(this.getPeriod()), 0, RoundingMode.HALF_DOWN)
                .multiply(BigDecimal.valueOf(savedDays));

        return interestAmount;
    }

    @Override
    public void deposit(BigDecimal amount) {
        this.setBalance(this.getBalance().add(amount));

    }

    @Override
    public LocalDateTime getDepositDate() {
        return getCreatedAt().plusDays(getPeriod());
    }

    @Override
    public String getType() {
        return "Tiết kiệm tích lũy";
    }

}
