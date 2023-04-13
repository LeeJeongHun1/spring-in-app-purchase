package com.springinapppurchase.component;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
@RequiredArgsConstructor
@Component
public class MessageManager {
    private final MessageSource messageSource;

    public String getMessage(String messageCode, String... args) {
        return messageSource.getMessage(messageCode, args, Locale.KOREAN);
    }
}
