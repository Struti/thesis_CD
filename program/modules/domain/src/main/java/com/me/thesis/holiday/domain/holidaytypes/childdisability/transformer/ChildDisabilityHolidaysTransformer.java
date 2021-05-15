package com.me.thesis.holiday.domain.holidaytypes.childdisability.transformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.dto.holidaytypes.ChildDisabilityHolidayDetailsDto;

/**
 * The type Child disability holidays transformer.
 */
@Component
public class ChildDisabilityHolidaysTransformer {

    /**
     * Transform child disability holiday details dto.
     *
     * @param dto the dto
     *
     * @return the child disability holiday details dto
     */
    public ChildDisabilityHolidayDetailsDto transform(ChildDisabilityHolidays dto) {
        return ChildDisabilityHolidayDetailsDto.builder()
            .id(dto.getId()
                .orElse(null))
            .type(dto.getType())
            .description(dto.getDescription())
            .childrenNumber(dto.getChildrenNumber())
            .day(dto.getDay())
            .build();
    }

    /**
     * Transform child disability holidays.
     *
     * @param dto the dto
     *
     * @return the child disability holidays
     */
    public ChildDisabilityHolidays transform(ChildDisabilityHolidayDetailsDto dto) {
        return ChildDisabilityHolidays.builder()
            .id(dto.getId()
                .orElse(null))
            .type(dto.getType())
            .description(dto.getDescription())
            .childrenNumber(dto.getChildrenNumber())
            .day(dto.getDay())
            .build();
    }

}
