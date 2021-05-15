package com.me.thesis.holiday.domain.holidaytypes.dad.transformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidaytypes.dad.domain.DadHolidays;
import com.me.thesis.holiday.dto.holidaytypes.DadHolidayDetailsDto;

/**
 * The type Dad holidays transformer.
 */
@Component
public class DadHolidaysTransformer {

    /**
     * Transform dad holiday details dto.
     *
     * @param domain the domain
     *
     * @return the dad holiday details dto
     */
    public DadHolidayDetailsDto transform(DadHolidays domain) {
        return DadHolidayDetailsDto.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .limit(domain.getLimit())
            .day(domain.getDay())
            .build();
    }

    /**
     * Transform dad holidays.
     *
     * @param dto the dto
     *
     * @return the dad holidays
     */
    public DadHolidays transform(DadHolidayDetailsDto dto) {
        return DadHolidays.builder()
            .id(dto.getId()
                .orElse(null))
            .type(dto.getType())
            .description(dto.getDescription())
            .limit(dto.getLimit())
            .day(dto.getDay())
            .build();
    }

}
