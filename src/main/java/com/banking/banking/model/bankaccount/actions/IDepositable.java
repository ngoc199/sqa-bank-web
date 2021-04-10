package com.banking.banking.model.bankaccount.actions;

import java.math.BigDecimal;

public interface IDepositable {
    /**
     * Deposit the amount of money to the destination account
     * @param amount
     */
    public void deposit(BigDecimal amount);
}
