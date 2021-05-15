package com.me.thesis.holiday.service.lib;

import java.time.Year;

import org.springframework.stereotype.Service;

/**
 * The type Year provider.
 */
@Service
public class YearProvider {

    /**
     * Provide year.
     *
     * @return the year
     */
    public Year provide() {
        return Year.now();
    }

}
