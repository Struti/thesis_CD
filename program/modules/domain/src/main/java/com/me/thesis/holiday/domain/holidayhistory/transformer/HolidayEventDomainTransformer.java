package com.me.thesis.holiday.domain.holidayhistory.transformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayEvent;
import com.me.thesis.holiday.dto.holidayhistory.HolidayEventDto;

/**
 * The type Holiday event domain transformer.
 */
@Component
public class HolidayEventDomainTransformer {

    /**
     * Transform holiday event.
     *
     * @param eventDto the event dto
     *
     * @return the holiday event
     */
    public HolidayEvent transform(HolidayEventDto eventDto) {
        return HolidayEvent.builder()
            .description(eventDto.getDescription())
            .startDate(eventDto.getStartDate())
            .endDate(eventDto.getEndDate())
            .dadHoliday(eventDto.getDadHoliday())
            .build();
    }

}
