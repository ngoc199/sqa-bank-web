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
        // TODO Auto-generated method stub

    }

    @Override
    protected BigDecimal getCurrentSavingsInterestAmount() {
        // Get the rate in month and round it down to 2 precisions (if necessary)
        BigDecimal rate = BigDecimal.valueOf(this.getRate()).divide(BigDecimal.valueOf(Constants.A_MONTH)).setScale(2,
                RoundingMode.FLOOR);

        // Calculate the saved days
        LocalDateTime createdAt = this.getCreatedAt();
        LocalDateTime today = LocalDateTime.now();
        long savedDays = ChronoUnit.DAYS.between(today, createdAt);

        // Calculate the total of this month
        BigDecimal q = rate.add(BigDecimal.valueOf(1));
        BigDecimal monthNum = BigDecimal.valueOf(this.getPeriod() / Constants.A_MONTH).setScale(0, RoundingMode.FLOOR);
        BigDecimal totalAmount = monthlyAmount.multiply(q)
                .multiply(((q).pow(monthNum.intValue())).subtract(BigDecimal.valueOf(1))).divide(rate);

        // Calculate the current interest amount using the formula
        // totalAmount / period (days) * savedDays
        BigDecimal interestAmount = totalAmount.divide(BigDecimal.valueOf(this.getPeriod()))
                .multiply(BigDecimal.valueOf(savedDays));

        return interestAmount;
    }

    @Override
    public void deposit(BigDecimal amount) {
        this.setBalance(this.getBalance().add(amount));

    }

}
