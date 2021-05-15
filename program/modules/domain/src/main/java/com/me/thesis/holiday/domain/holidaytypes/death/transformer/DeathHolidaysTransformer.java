package com.me.thesis.holiday.domain.holidaytypes.death.transformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidaytypes.death.domain.DeathHolidays;
import com.me.thesis.holiday.dto.holidaytypes.DeathHolidayDetailsDto;

/**
 * The type Death holidays transformer.
 */
@Component
public class DeathHolidaysTransformer {

    /**
     * Transform death holiday details dto.
     *
     * @param domain the domain
     *
     * @return the death holiday details dto
     */
    public DeathHolidayDetailsDto transform(DeathHolidays domain) {
        return DeathHolidayDetailsDto.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .day(domain.getDay())
            .build();
    }

    /**
     * Transform death holidays.
     *
     * @param dto the dto
     *
     * @return the death holidays
     */
    public DeathHolidays transform(DeathHolidayDetailsDto dto) {
        return DeathHolidays.builder()
            .id(dto.getId()
                .orElse(null))
            .type(dto.getType())
            .description(dto.getDescription())
            .day(dto.getDay())
            .build();
    }

}
