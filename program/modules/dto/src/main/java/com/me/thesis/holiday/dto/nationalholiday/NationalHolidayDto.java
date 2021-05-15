package com.me.thesis.holiday.dto.nationalholiday;

import java.time.LocalDate;
import java.util.Optional;

import lombok.Builder;
import lombok.Value;

/**
 * The type National holiday dto.
 */
@Value
@Builder
public class NationalHolidayDto {

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
