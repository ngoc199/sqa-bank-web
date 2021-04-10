package com.banking.banking.util.logger;

import com.banking.banking.common.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    public static final Logger LOGIN_ERROR_LOGGER = LoggerFactory.getLogger(Constants.LOGIN_ERROR_LOGGER_NAME);
    public static final Logger BANK_ACCOUNT_UPDATE_LOGGER = LoggerFactory.getLogger(Constants.BANK_ACCOUNT_UPDATE_LOGGER_NAME);
    public static final Logger TRANSACTION_LOGGER = LoggerFactory.getLogger(Constants.TRANSACTION_LOGGER_NAME);
    public static final Logger CONFIG_UPDATE_LOGGER = LoggerFactory.getLogger(Constants.CONFIG_UPDATE_LOGGER_NAME);
}
