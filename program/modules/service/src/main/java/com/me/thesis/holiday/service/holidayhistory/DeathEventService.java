package com.me.thesis.holiday.service.holidayhistory;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.domain.holidayhistory.domain.EventRelatedHoliday;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidaytypes.EventRelatedHolidayType;
import com.me.thesis.holiday.domain.holidaytypes.death.domain.DeathHolidays;
import com.me.thesis.holiday.dto.holidayhistory.DeathEventDto;
import com.me.thesis.holiday.service.holidaytypes.DeathHolidaysService;

@Service
public class DeathEventService {

    @Autowired
    private HolidayHistorySaveService historySaveService;

    @Autowired
    private DeathHolidaysService deathHolidaysService;

    @Autowired
    private HolidayHistoryService holidayHistoryService;

    public void addEvent(DeathEventDto eventDto) {
        HolidayHistory history = getHistory(eventDto.getPersonId());
        DeathHolidays deathHolidays = getDeathHolidays();
        EventRelatedHoliday eventRelatedHoliday = getEventRelatedHoliday(deathHolidays.getDay(), eventDto.getEventDate());
        save(eventDto, history, eventRelatedHoliday);
    }

    private HolidayHistory getHistory(Long personId) {
        return holidayHistoryService.getHistoryByPersonId(personId);
    }

    private DeathHolidays getDeathHolidays() {
        return deathHolidaysService.getAllDeathHolidayDetails()
            .stream()
            .findFirst()
            .orElse(null);
    }

    private EventRelatedHoliday getEventRelatedHoliday(int days, LocalDate eventDate) {
        return EventRelatedHoliday.builder()
            .holidayType(EventRelatedHolidayType.DEATH)
            .days(days)
            .eventDate(eventDate)
            .build();
    }

    private void save(DeathEventDto eventDto, HolidayHistory history, EventRelatedHoliday eventRelatedHoliday) {
        historySaveService.saveHistory(history, eventRelatedHoliday, eventDto.getEventDate()
            .getYear());
    }

}
