package com.banking.banking.util.message;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageUtil {
    private final MessageSource messageSource;

    public String getMessage(String messageName) {
        return messageSource.getMessage(messageName, null, LocaleContextHolder.getLocale());
    }
}
