package com.banking.banking.model.bankaccount.loanaccount;

import static com.banking.banking.model.bankaccount.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import com.banking.banking.model.bankaccount.loanAccount.FixedLoanAccount;
import com.banking.banking.model.bankaccount.loanAccount.LoanAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedLoanAccountTest {

    private LoanAccount account;

    @BeforeEach
    public void setup() {
        account = new FixedLoanAccount();
        account.setBalance(VALID_BALANCE);
        account.setRate(VALID_RATE);
    }

    @Test
    public void getCurrentPayingAmount_BalanceIs0_Return0() {
        account.setBalance(BigDecimal.valueOf(0));
        BigDecimal expected = BigDecimal.valueOf(0);
        BigDecimal actual = account.getCurrentPayingAmount();
        assertEquals(expected, actual);
    }

    @Test
    public void getCurrentPayingAmount_RateLessThan0001_Return0() {
        account.setRate(0);
        BigDecimal expected = BigDecimal.valueOf(0);
        BigDecimal actual = account.getCurrentPayingAmount();
        assertEquals(expected, actual);
    }

    @Test
    public void getCurrentPayingAmount_RateGreaterThan05_Return0() {
        account.setRate(0.51f);
        BigDecimal expected = BigDecimal.valueOf(0);
        BigDecimal actual = account.getCurrentPayingAmount();
        assertEquals(expected, actual);
    }

    @Test
    public void getCurrentPayingAmount_ValidFields_Return833() {
        BigDecimal expected = BigDecimal.valueOf(833);
        BigDecimal actual = account.getCurrentPayingAmount();
        assertEquals(expected, actual);
    }

}
