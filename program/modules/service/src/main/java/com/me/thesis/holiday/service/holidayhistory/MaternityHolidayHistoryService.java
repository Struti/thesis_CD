package com.me.thesis.holiday.service.holidayhistory;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.holidayhistory.domain.EventRelatedHoliday;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidaytypes.EventRelatedHolidayType;
import com.me.thesis.holiday.domain.holidaytypes.maternity.domain.MaternityHoliday;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.holidaytypes.MaternityHolidaysService;

@Service
public class MaternityHolidayHistoryService {

    @Autowired
    private HolidayHistorySaveService historySaveService;

    @Autowired
    private MaternityHolidaysService maternityHolidaysService;

    public void addHolidays(List<ChildDetails> childDetails, LocalDate currentDate) {
        if (!childDetails.isEmpty()) {
            MaternityHoliday maternityHoliday = getMaternityHoliday();
            Map<HolidayHistory, LocalDate> expectedDateMap = getExpectedDateMap(childDetails);
            createAndSave(currentDate, maternityHoliday, expectedDateMap);
        }
    }

    private MaternityHoliday getMaternityHoliday() {
        return maternityHolidaysService.getAllMaternityHolidays()
            .stream()
            .findFirst()
            .orElse(null);
    }

    private Map<HolidayHistory, LocalDate> getExpectedDateMap(List<ChildDetails> childDetails) {
        return childDetails.stream()
            .collect(Collectors.toMap(this::getHistory, this::getExpectedDate));
    }

    private void createAndSave(LocalDate currentDate, MaternityHoliday maternityHoliday, Map<HolidayHistory, LocalDate> expectedDateMap) {
        expectedDateMap.forEach(
            (holidayHistory, eventDate) -> {
                EventRelatedHoliday eventRelatedHoliday = getEventRelatedHoliday(maternityHoliday, eventDate);
                save(currentDate, holidayHistory, eventRelatedHoliday);
            }
        );
    }

    private HolidayHistory getHistory(ChildDetails recentlyBornChild) {
        return Optional.ofNullable(recentlyBornChild)
            .map(ChildDetails::getPerson)
            .map(PersonDetails::getHolidayHistory)
            .orElse(null);
    }

    private LocalDate getExpectedDate(ChildDetails child) {
        return child.getExpectedDate()
            .orElse(null);
    }

    private EventRelatedHoliday getEventRelatedHoliday(MaternityHoliday maternityHoliday, LocalDate eventDate) {
        return EventRelatedHoliday.builder()
            .holidayType(EventRelatedHolidayType.MATERNITY)
            .eventDate(eventDate)
            .days(maternityHoliday.getBeforeDays() + maternityHoliday.getAfterDays())
            .build();
    }

    private void save(LocalDate currentDate, HolidayHistory holidayHistory, EventRelatedHoliday eventRelatedHoliday) {
        historySaveService.saveHistory(holidayHistory, eventRelatedHoliday, currentDate.getYear());
    }

}
