package com.me.thesis.holiday.dto.holidaytypes;

import java.util.Optional;

import lombok.Builder;
import lombok.Value;

/**
 * The type Children holiday details dto.
 */
@Value
@Builder
public class ChildrenHolidayDetailsDto {

    Long id;
    String type;
    String description;
    int children;
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
