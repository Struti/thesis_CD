package com.me.thesis.holiday.service.user.exceptions;

/**
 * The type Email exist exception.
 */
public class EmailExistException extends RuntimeException {

    /**
     * Instantiates a new Email exist exception.
     */
    public EmailExistException() {
        super("Email already exist");
    }

}
