package com.me.thesis.holiday.service.password;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

/**
 * The type Password generator.
 */
@Component
public class PasswordGenerator {

    private static final int PASSWORD_LENGTH = 8;
    private static final int RANDOM_NUMBER_ORIGIN = 48;
    private static final int RANDOM_NUMBER_BOUND = 123;

    /**
     * Create first password string.
     *
     * @return the string
     */
    public String createFirstPassword() {
        return new SecureRandom()
            .ints(RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND)
            .filter(value -> Character.isAlphabetic(value) || Character.isDigit(value))
            .limit(PASSWORD_LENGTH)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }

}
