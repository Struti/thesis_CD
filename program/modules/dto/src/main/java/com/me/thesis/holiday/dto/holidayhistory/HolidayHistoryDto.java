package com.me.thesis.holiday.dto.holidayhistory;

import lombok.Builder;
import lombok.Value;

/**
 * The type Holiday history dto.
 */
@Value
@Builder
public class HolidayHistoryDto {

    Long holidayHistoryId;
    Long personId;

}
