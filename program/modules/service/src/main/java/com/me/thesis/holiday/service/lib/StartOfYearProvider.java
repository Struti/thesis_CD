package com.me.thesis.holiday.service.lib;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Start of year provider.
 */
@Service
public class StartOfYearProvider {

    private static final int ONE = 1;

    @Autowired
    private YearProvider yearProvider;

    /**
     * Provide local date.
     *
     * @return the local date
     */
    public LocalDate provide() {
        return LocalDate.of(yearProvider.provide()
            .getValue(), ONE, ONE);
    }

}
