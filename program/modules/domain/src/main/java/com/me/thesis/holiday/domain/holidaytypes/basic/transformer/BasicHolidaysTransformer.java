package com.me.thesis.holiday.domain.holidaytypes.basic.transformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.dto.holidaytypes.BasicHolidayDetailsDto;

/**
 * The type Basic holidays transformer.
 */
@Component
public class BasicHolidaysTransformer {

    /**
     * Transform basic holiday details dto.
     *
     * @param domain the domain
     *
     * @return the basic holiday details dto
     */
    public BasicHolidayDetailsDto transform(BasicHolidays domain) {
        return BasicHolidayDetailsDto.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .day(domain.getDay())
            .build();
    }

    /**
     * Transform basic holidays.
     *
     * @param dto the dto
     *
     * @return the basic holidays
     */
    public BasicHolidays transform(BasicHolidayDetailsDto dto) {
        return BasicHolidays.builder()
            .id(dto.getId()
                .orElse(null))
            .type(dto.getType())
            .description(dto.getDescription())
            .day(dto.getDay())
            .build();
    }

}
