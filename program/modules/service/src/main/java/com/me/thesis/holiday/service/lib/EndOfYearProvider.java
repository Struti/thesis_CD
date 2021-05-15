package com.me.thesis.holiday.service.lib;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type End of year provider.
 */
@Service
public class EndOfYearProvider {

    private static final int MONTH = 12;
    private static final int DAY = 31;

    @Autowired
    private YearProvider yearProvider;

    /**
     * Provide local date.
     *
     * @return the local date
     */
    public LocalDate provide() {
        return LocalDate.of(yearProvider.provide()
            .getValue(), MONTH, DAY);
    }

}
