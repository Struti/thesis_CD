package com.me.thesis.holiday.dto.holidaytypes;

import java.util.Optional;

import lombok.Builder;
import lombok.Value;

/**
 * The type Maternity holiday details dto.
 */
@Value
@Builder
public class MaternityHolidayDetailsDto {

    Long id;
    String type;
    String description;
    int beforeDate;
    int afterDate;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

}
