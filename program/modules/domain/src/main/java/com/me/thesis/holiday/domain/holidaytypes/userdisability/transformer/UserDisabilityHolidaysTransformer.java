package com.me.thesis.holiday.domain.holidaytypes.userdisability.transformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;
import com.me.thesis.holiday.dto.holidaytypes.UserDisabilityHolidayDetailsDto;

/**
 * The type User disability holidays transformer.
 */
@Component
public class UserDisabilityHolidaysTransformer {

    /**
     * Transform user disability holiday details dto.
     *
     * @param domain the domain
     *
     * @return the user disability holiday details dto
     */
    public UserDisabilityHolidayDetailsDto transform(UserDisabilityHolidays domain) {
        return UserDisabilityHolidayDetailsDto.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .day(domain.getDay())
            .build();
    }

    /**
     * Transform user disability holidays.
     *
     * @param dto the dto
     *
     * @return the user disability holidays
     */
    public UserDisabilityHolidays transform(UserDisabilityHolidayDetailsDto dto) {
        return UserDisabilityHolidays.builder()
            .id(dto.getId()
                .orElse(null))
            .type(dto.getType())
            .description(dto.getDescription())
            .day(dto.getDay())
            .build();
    }

}
