package com.me.thesis.holiday.domain.nationalholiday.domain;

import java.time.LocalDate;
import java.util.Optional;

import lombok.Builder;
import lombok.Value;

/**
 * The type National holiday.
 */
@Value
@Builder
public class NationalHoliday {

    Long id;
    LocalDate date;
    boolean fix;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

}
