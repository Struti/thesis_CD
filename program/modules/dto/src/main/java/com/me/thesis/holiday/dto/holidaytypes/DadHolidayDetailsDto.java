package com.me.thesis.holiday.dto.holidaytypes;

import java.util.Optional;

import lombok.Builder;
import lombok.Value;

/**
 * The type Dad holiday details dto.
 */
@Value
@Builder
public class DadHolidayDetailsDto {

    Long id;
    String type;
    String description;
    int limit;
    int day;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

}
