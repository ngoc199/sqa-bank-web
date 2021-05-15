package com.banking.banking.model.bankaccount.savingsAccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Store the constants for testing the savings account
 */
class TestConstants {
    public static final BigDecimal VALID_BALANCE = BigDecimal.valueOf(1000000);
    public static final float VALID_RATE = 0.01f;
    public static final LocalDateTime VALID_CREATE_DATE = LocalDateTime.now().minusDays(1);
    public static final int VALID_PERIOD = 365;
    public static final float VALID_LOWEST_RATE = 0.001f;
}
