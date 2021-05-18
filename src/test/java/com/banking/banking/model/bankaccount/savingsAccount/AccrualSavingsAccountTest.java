package com.banking.banking.model.bankaccount.savingsAccount;

import static com.banking.banking.model.bankaccount.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccrualSavingsAccountTest {
     private AccrualSavingsAccount account;

    /**
     * Create the valid savings account before each test.
     * The test can modify the field to invalid on use.
     */
    @BeforeEach
    public void setup() {
        account = new AccrualSavingsAccount();
        account.setBalance(VALID_BALANCE);
        account.setMonthlyAmount(VALID_BALANCE);
        account.setRate(VALID_RATE);
        account.setCreatedAt(VALID_CREATE_DATE);
        account.setPeriod(VALID_PERIOD);
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
    public void getCurrentSavingsInterestAmount_ValidFields_Return5430() {
        account.setCreatedAt(LocalDateTime.now().minusDays(30));
        account.setPeriod(360);
        BigDecimal expected = BigDecimal.valueOf(5430);
        BigDecimal actual = account.getCurrentSavingsInterestAmount();
        assertEquals(expected, actual);
    }
}
