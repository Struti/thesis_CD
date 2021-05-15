package com.me.thesis.holiday.domain.holidaytypes.maternity.transformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidaytypes.maternity.domain.MaternityHoliday;
import com.me.thesis.holiday.dto.holidaytypes.MaternityHolidayDetailsDto;

/**
 * The type Maternity holidays transformer.
 */
@Component
public class MaternityHolidaysTransformer {

    /**
     * Transform maternity holiday details dto.
     *
     * @param domain the domain
     *
     * @return the maternity holiday details dto
     */
    public MaternityHolidayDetailsDto transform(MaternityHoliday domain) {
        return MaternityHolidayDetailsDto.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .type(domain.getType())
            .description(domain.getDescription())
            .beforeDate(domain.getBeforeDays())
            .afterDate(domain.getAfterDays())
            .build();
    }

    /**
     * Transform maternity holiday.
     *
     * @param dto the dto
     *
     * @return the maternity holiday
     */
    public MaternityHoliday transform(MaternityHolidayDetailsDto dto) {
        return MaternityHoliday.builder()
            .id(dto.getId()
                .orElse(null))
            .type(dto.getType())
            .type(dto.getType())
            .description(dto.getDescription())
            .beforeDays(dto.getBeforeDate())
            .afterDays(dto.getAfterDate())
            .build();
    }

}
