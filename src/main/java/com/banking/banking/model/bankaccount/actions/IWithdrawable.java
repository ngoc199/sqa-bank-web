package com.banking.banking.model.bankaccount.actions;

import java.math.BigDecimal;


public interface IWithdrawable {
    /**
     * Withdraw the amount of the money
     * @param amount
     */
    public void withdraw(BigDecimal amount);

    /**
     * Withdraw all of the money in the account
     */
    public void withdrawAll();
}
