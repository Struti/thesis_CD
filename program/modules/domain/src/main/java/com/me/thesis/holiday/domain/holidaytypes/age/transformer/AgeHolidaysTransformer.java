package com.me.thesis.holiday.domain.holidaytypes.age.transformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;
import com.me.thesis.holiday.dto.holidaytypes.AgeHolidayDetailsDto;

/**
 * The type Age holidays transformer.
 */
@Component
public class AgeHolidaysTransformer {

    /**
     * Transform age holiday details dto.
     *
     * @param domain the domain
     *
     * @return the age holiday details dto
     */
    public AgeHolidayDetailsDto transform(AgeBasedHolidays domain) {
        return AgeHolidayDetailsDto.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .age(domain.getAge())
            .day(domain.getDay())
            .build();
    }

    /**
     * Transform age based holidays.
     *
     * @param dto the dto
     *
     * @return the age based holidays
     */
    public AgeBasedHolidays transform(AgeHolidayDetailsDto dto) {
        return AgeBasedHolidays.builder()
            .id(dto.getId()
                .orElse(null))
            .type(dto.getType())
            .description(dto.getDescription())
            .age(dto.getAge())
            .day(dto.getDay())
            .build();
    }

}
