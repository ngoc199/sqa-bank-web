package com.banking.banking.model.bankaccount.savingsAccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.banking.banking.model.bankaccount.BankAccount;
import com.banking.banking.model.bankaccount.actions.IWithdrawable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "savings_accounts")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class SavingsAccount extends BankAccount implements IWithdrawable {
    private float rate; // Calculated in percent
    private int period; // Counted by days

    private BigDecimal moneyReceive;

    /**
     * Get the savings interest amount till today
     *
     * @return savingsAmountInterest
     */
    public abstract BigDecimal getCurrentSavingsInterestAmount();

    /**
     * Get the deposit date of the account. Return null if the deposit date is not supported
     * @return depositDate
     */
    public abstract LocalDateTime getDepositDate();

    /**
     * Get the percent value of the rate
     *
     * @return rateInPercentValue
     */
    public float getRatePercentValue() {
        return rate * 100;
    }

    /**
     * Check if the account's balance and rate are valid
     * @return isValid
     */
    protected boolean isValidAccount() {

        // The balance must be greater than 0
        // And the rate must be in [0.001,0.5]
        // And the period must be greater than 0
        return !(getBalance().compareTo(BigDecimal.valueOf(0)) < 1 || rate < 0.001 || rate > 0.5 || period <= 0);
    }

}
