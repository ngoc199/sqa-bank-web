package com.banking.banking.model.bankaccount.savingsAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.banking.banking.model.bankaccount.savingsAccount.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FixedSavingsAccountTest {
    private FixedSavingsAccount account;

    /**
     * Create the valid savings account before each test.
     * The test can modify the field to invalid on use.
     */
    @BeforeEach
    public void setup() {
        account = new FixedSavingsAccount();
        account.setBalance(VALID_BALANCE);
        account.setRate(VALID_RATE);
        account.setCreatedAt(VALID_CREATE_DATE);
        account.setPeriod(VALID_PERIOD);
        account.setLowestRate(VALID_LOWEST_RATE);
    }


    @Test
    public void getCurrentSavingsInterestAmount_BalanceIs0_Return0() {
        account.setBalance(BigDecimal.valueOf(0));
        BigDecimal expected = BigDecimal.valueOf(0);
        BigDecimal actual = account.getCurrentSavingsInterestAmount();
        assertEquals(expected, actual);
    }

    @Test
    public void getCurrentSavingsInterestAmount_RateLessThan0001_Return0() {
        account.setRate(0);
        BigDecimal expected = BigDecimal.valueOf(0);
        BigDecimal actual = account.getCurrentSavingsInterestAmount();
        assertEquals(expected, actual);
    }

    @Test
    public void getCurrentSavingsInterestAmount_RateGreaterThan05_Return0() {
        account.setRate(0.51f);
        BigDecimal expected = BigDecimal.valueOf(0);
        BigDecimal actual = account.getCurrentSavingsInterestAmount();
        assertEquals(expected, actual);
    }

    @Test
    public void getCurrentSavingsInterestAmount_PeriodLessThanOrEqual0_Return0() {
        account.setPeriod(0);
        BigDecimal expected = BigDecimal.valueOf(0);
        BigDecimal actual = account.getCurrentSavingsInterestAmount();
        assertEquals(expected, actual);
    }

    @Test
    public void getCurrentSavingsInterestAmount_CreateDateGreaterThanOrEqualToday_Return0() {
        account.setCreatedAt(LocalDateTime.now());
        BigDecimal expected = BigDecimal.valueOf(0);
        BigDecimal actual = account.getCurrentSavingsInterestAmount();
        assertEquals(expected, actual);
    }

    @Test
    public void getCurrentSavingsInterestAmount_SavedDaysLessThanOrEqualToPeriod_Return10000() {
        account.setCreatedAt(LocalDateTime.now().minusDays(account.getPeriod()));
        BigDecimal expected = BigDecimal.valueOf(10000);
        BigDecimal actual = account.getCurrentSavingsInterestAmount();
        assertEquals(expected, actual);
    }

    @Test
    public void getCurrentSavingsInterestAmount_SavedDaysGreaterThanPeriod_Return10003() {
        account.setCreatedAt(LocalDateTime.now().minusDays(account.getPeriod() + 1));
        BigDecimal expected = BigDecimal.valueOf(10003);
        BigDecimal actual = account.getCurrentSavingsInterestAmount();
        assertEquals(expected, actual);
    }
}
