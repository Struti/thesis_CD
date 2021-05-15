package com.me.thesis.holiday.service.user.exceptions;

/**
 * The type Password change failed exception.
 */
public class PasswordChangeFailedException extends RuntimeException {

    /**
     * Instantiates a new Password change failed exception.
     */
    public PasswordChangeFailedException() {
        super("Password change failed");
    }

}
