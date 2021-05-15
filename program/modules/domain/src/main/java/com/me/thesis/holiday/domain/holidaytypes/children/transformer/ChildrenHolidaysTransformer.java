package com.me.thesis.holiday.domain.holidaytypes.children.transformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.dto.holidaytypes.ChildrenHolidayDetailsDto;

/**
 * The type Children holidays transformer.
 */
@Component
public class ChildrenHolidaysTransformer {

    /**
     * Transform children holiday details dto.
     *
     * @param domain the domain
     *
     * @return the children holiday details dto
     */
    public ChildrenHolidayDetailsDto transform(ChildrenHolidays domain) {
        return ChildrenHolidayDetailsDto.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .children(domain.getChildren())
            .day(domain.getDay())
            .build();
    }

    /**
     * Transform children holidays.
     *
     * @param dto the dto
     *
     * @return the children holidays
     */
    public ChildrenHolidays transform(ChildrenHolidayDetailsDto dto) {
        return ChildrenHolidays.builder()
            .id(dto.getId()
                .orElse(null))
            .type(dto.getType())
            .description(dto.getDescription())
            .children(dto.getChildren())
            .day(dto.getDay())
            .build();
    }

}
