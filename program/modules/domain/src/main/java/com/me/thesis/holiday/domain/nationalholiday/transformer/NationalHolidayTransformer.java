package com.me.thesis.holiday.domain.nationalholiday.transformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;
import com.me.thesis.holiday.dto.nationalholiday.NationalHolidayDto;

/**
 * The type National holiday transformer.
 */
@Component
public class NationalHolidayTransformer {

    /**
     * Transform national holiday.
     *
     * @param nationalHoliday the national holiday
     *
     * @return the national holiday
     */
    public NationalHoliday transform(NationalHolidayDto nationalHoliday) {
        return NationalHoliday.builder()
            .id(nationalHoliday.getId()
                .orElse(null))
            .date(nationalHoliday.getDate())
            .fix(nationalHoliday.isFix())
            .build();
    }

}
