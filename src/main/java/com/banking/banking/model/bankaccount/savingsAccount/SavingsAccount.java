package com.banking.banking.model.bankaccount.savingsAccount;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.banking.banking.model.bankaccount.BankAccount;
import com.banking.banking.model.bankaccount.actions.IWithdrawable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
    protected abstract BigDecimal getCurrentSavingsInterestAmount();

}
