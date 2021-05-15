package com.me.thesis.holiday.dal.nationalholiday.transformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.nationalholiday.domain.NationalHolidayEntity;
import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;

/**
 * The type National holiday entity transformer.
 */
@Component
public class NationalHolidayEntityTransformer {

    /**
     * Transform national holiday entity.
     *
     * @param nationalHoliday the national holiday
     *
     * @return the national holiday entity
     */
    public NationalHolidayEntity transform(NationalHoliday nationalHoliday) {
        return NationalHolidayEntity.builder()
            .id(nationalHoliday.getId()
                .orElse(null))
            .date(nationalHoliday.getDate())
            .fix(nationalHoliday.isFix())
            .build();
    }

    /**
     * Transform national holiday.
     *
     * @param nationalHoliday the national holiday
     *
     * @return the national holiday
     */
    public NationalHoliday transform(NationalHolidayEntity nationalHoliday) {
        return NationalHoliday.builder()
            .id(nationalHoliday.getId())
            .date(nationalHoliday.getDate())
            .fix(nationalHoliday.isFix())
            .build();
    }

}
