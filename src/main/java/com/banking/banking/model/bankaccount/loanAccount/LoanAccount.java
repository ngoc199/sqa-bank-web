package com.banking.banking.model.bankaccount.loanAccount;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.banking.banking.model.bankaccount.BankAccount;
import com.banking.banking.model.bankaccount.actions.IDepositable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "loan_accounts")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class LoanAccount extends BankAccount implements IDepositable {
    private float rate;

    private BigDecimal moneyPay;

    private LocalDate nextPayDate;
    private LocalDate realPayDate;
    private LocalDate payDeadline;
    private int deadlineModifiedCounter;

    /**
     * Calculate the current month paying amount
     * @return currentPayingAmount
     */
    public abstract BigDecimal getCurrentPayingAmount();

    /**
     * Check if the account's balance and rate are valid
     * @return isValid
     */
    protected boolean isValidAccount() {

        // The balance must be greater than 0
        // And the rate must be in [0.001,0.5]
        return !(getBalance().compareTo(BigDecimal.valueOf(0)) < 1 || rate < 0.001 || rate > 0.5);
    }

}
