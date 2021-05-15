package com.me.thesis.holiday.service.lib;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

/**
 * The type Current date provider.
 */
@Component
public class CurrentDateProvider {

    /**
     * Provide local date.
     *
     * @return the local date
     */
    public LocalDate provide() {
        return LocalDate.now();
    }

}
