package com.me.thesis.holiday.dto.holidayplan;

import java.util.List;

import com.me.thesis.holiday.dto.holidaytypes.BasicHolidayDetailsDto;

import lombok.Builder;
import lombok.Value;

/**
 * The type Plan dto.
 */
@Value
@Builder
public class PlanDto {

    BasicHolidayDetailsDto basicHolidays;
    List<HolidayType> fixHolidays;

}
