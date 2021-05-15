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
import com.me.thesis.holiday.domain.holidaytypes.dad.domain.DadHolidays;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.holidaytypes.DadHolidaysService;

/**
 * The type Dad holidays history service.
 */
@Service
public class DadHolidaysHistoryService {

    private static final int DAYS_TO_SUBTRACT = 1;
    private static final EventRelatedHolidayType DAD_HOLIDAY = EventRelatedHolidayType.DAD;

    @Autowired
    private DadHolidaysService dadHolidaysService;

    @Autowired
    private HolidayHistoryService holidayHistoryService;

    @Autowired
    private HolidayHistorySaveService historySaveService;

    /**
     * Add holidays.
     *
     * @param childDetails the child details
     * @param currentDate  the current date
     */
    public void addHolidays(List<ChildDetails> childDetails, LocalDate currentDate) {
        if (!childDetails.isEmpty()) {
            List<ChildDetails> recentlyBornChildren = getRecentlyBornChildren(childDetails, currentDate);
            DadHolidays dadHolidays = getDadHolidays();
            Map<Long, EventRelatedHoliday> eventRelatedHolidayMap = getEventRelatedHolidayMap(recentlyBornChildren, dadHolidays);
            save(currentDate, eventRelatedHolidayMap);
        }
    }

    private List<ChildDetails> getRecentlyBornChildren(List<ChildDetails> allChildren, LocalDate currentDate) {
        return allChildren.stream()
            .filter(child -> isBornInThisDays(currentDate, child))
            .collect(Collectors.toList());
    }

    private DadHolidays getDadHolidays() {
        return dadHolidaysService.getAllDadHolidays()
            .stream()
            .findFirst()
            .orElse(null);
    }

    private Map<Long, EventRelatedHoliday> getEventRelatedHolidayMap(List<ChildDetails> recentlyBornChildren, DadHolidays dadHolidays) {
        return recentlyBornChildren.stream()
            .collect(Collectors.toMap(this::getHistoryId, child -> createHolidayHistoryEntry(child, dadHolidays)));
    }

    private void save(LocalDate currentDate, Map<Long, EventRelatedHoliday> eventRelatedHolidayMap) {
        eventRelatedHolidayMap
            .forEach((holidayHistoryId, eventRelatedHoliday) -> {
                HolidayHistory history = holidayHistoryService.getHistory(holidayHistoryId);
                historySaveService.saveHistory(history, eventRelatedHoliday, currentDate.getYear());
            });
    }

    private boolean isBornInThisDays(LocalDate currentDate, ChildDetails child) {
        LocalDate childBirthDate = getChildBirthDate(child);
        return childBirthDate.isEqual(currentDate) || childBirthDate.isEqual(currentDate.minusDays(DAYS_TO_SUBTRACT));
    }

    private Long getHistoryId(ChildDetails recentlyBornChild) {
        return Optional.ofNullable(recentlyBornChild)
            .map(ChildDetails::getPerson)
            .map(PersonDetails::getHolidayHistory)
            .map(HolidayHistory::getId)
            .orElse(null);
    }

    private EventRelatedHoliday createHolidayHistoryEntry(ChildDetails child, DadHolidays dadHolidays) {
        LocalDate childBirthDate = getChildBirthDate(child);
        return EventRelatedHoliday.builder()
            .holidayType(DAD_HOLIDAY)
            .days(dadHolidays.getDay())
            .eventDate(childBirthDate)
            .expiration(getExpiration(childBirthDate, dadHolidays.getLimit()))
            .build();
    }

    private LocalDate getChildBirthDate(ChildDetails child) {
        return child.getBirthDate()
            .orElse(null);
    }

    private LocalDate getExpiration(LocalDate childBirthDate, int limit) {
        return childBirthDate.plusMonths(limit);
    }

}
