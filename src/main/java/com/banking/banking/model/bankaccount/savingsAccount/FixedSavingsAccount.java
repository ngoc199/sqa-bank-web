package com.banking.banking.model.bankaccount.savingsAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.banking.banking.common.Constants;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fixed_savings_accounts")
@Getter
@Setter
public class FixedSavingsAccount extends SavingsAccount {

    private float lowestRate;

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
        BigDecimal lowestRateDecimal = BigDecimal.valueOf(this.lowestRate).setScale(3, RoundingMode.FLOOR);

        // Calculate the saved days
        LocalDateTime createdAt = this.getCreatedAt();
        LocalDateTime today = LocalDateTime.now();
        long savedDays = ChronoUnit.DAYS.between(createdAt, today);
        if (savedDays <= 0) {
            return BigDecimal.valueOf(0);
        }

        BigDecimal interestAmount;
        if (savedDays > this.getPeriod()) {
            // Calculate the current interest amount using the formula
            // balance * (rate * (savedDays // period * period) + lowestRate * (savedDays %
            // period)) / a_year
            interestAmount = balance
                    .multiply(rate
                            .multiply(BigDecimal.valueOf(savedDays)
                                    .divide(BigDecimal.valueOf(this.getPeriod()), 0, RoundingMode.FLOOR)
                                    .multiply(BigDecimal.valueOf(this.getPeriod())))
                            .add(lowestRateDecimal.multiply(
                                    BigDecimal.valueOf(savedDays).remainder(BigDecimal.valueOf(this.getPeriod())))))
                    .divide(BigDecimal.valueOf(Constants.A_YEAR), 0, RoundingMode.HALF_DOWN);

        } else {
            // Calculate the current interest amount using the formula
            // balance * rate * savedDays / a_year
            interestAmount = balance.multiply(rate).multiply(BigDecimal.valueOf(savedDays))
                    .divide(BigDecimal.valueOf(Constants.A_YEAR), 0, RoundingMode.HALF_DOWN);

        }

        return interestAmount;
    }

}
