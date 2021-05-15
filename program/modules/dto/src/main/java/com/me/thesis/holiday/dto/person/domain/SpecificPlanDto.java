package com.me.thesis.holiday.dto.person.domain;

import java.util.Optional;

import com.me.thesis.holiday.dto.holidaytypes.AgeHolidayDetailsDto;
import com.me.thesis.holiday.dto.holidaytypes.BasicHolidayDetailsDto;
import com.me.thesis.holiday.dto.holidaytypes.ChildDisabilityHolidayDetailsDto;
import com.me.thesis.holiday.dto.holidaytypes.ChildrenHolidayDetailsDto;
import com.me.thesis.holiday.dto.holidaytypes.UserDisabilityHolidayDetailsDto;

import lombok.Builder;
import lombok.Value;

/**
 * The type Specific plan dto.
 */
@Value
@Builder
public class SpecificPlanDto {

    Long id;
    BasicHolidayDetailsDto basicHolidays;
    AgeHolidayDetailsDto ageBasedHolidays;
    UserDisabilityHolidayDetailsDto userDisabilityHolidays;
    ChildrenHolidayDetailsDto childrenHolidays;
    ChildDisabilityHolidayDetailsDto childDisabilityHolidays;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

    /**
     * Gets age based holidays.
     *
     * @return the age based holidays
     */
    public Optional<AgeHolidayDetailsDto> getAgeBasedHolidays() {
        return Optional.ofNullable(ageBasedHolidays);
    }

    /**
     * Gets user disability holidays.
     *
     * @return the user disability holidays
     */
    public Optional<UserDisabilityHolidayDetailsDto> getUserDisabilityHolidays() {
        return Optional.ofNullable(userDisabilityHolidays);
    }

    /**
     * Gets children holidays.
     *
     * @return the children holidays
     */
    public Optional<ChildrenHolidayDetailsDto> getChildrenHolidays() {
        return Optional.ofNullable(childrenHolidays);
    }

    /**
     * Gets child disability holidays.
     *
     * @return the child disability holidays
     */
    public Optional<ChildDisabilityHolidayDetailsDto> getChildDisabilityHolidays() {
        return Optional.ofNullable(childDisabilityHolidays);
    }

}
