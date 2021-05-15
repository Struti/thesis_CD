package com.me.thesis.holiday.dto.holidayhistory;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Value;

/**
 * The type Holiday event dto.
 */
@Value
@Builder
public class HolidayEventDto {

    String description;
    LocalDate startDate;
    LocalDate endDate;
    Boolean dadHoliday;

}
