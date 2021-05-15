package com.me.thesis.holiday.service.holidayhistory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.domain.holidayhistory.domain.EventRelatedHoliday;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayEvent;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;

/**
 * The type Holiday history save service.
 */
@Service
public class HolidayHistorySaveService {

    @Autowired
    private HolidayHistoryService service;

    /**
     * Save history.
     *
     * @param holidayHistory      the holiday history
     * @param eventRelatedHoliday the event related holiday
     * @param year                the year
     */
    public void saveHistory(HolidayHistory holidayHistory, EventRelatedHoliday eventRelatedHoliday, int year) {
        List<YearHistory> yearHistories = new ArrayList<>(List.copyOf(holidayHistory.getYearHistories()));
        Map<Integer, List<EventRelatedHoliday>> yearMap = getYearMapEventRelatedHolidays(holidayHistory);
        List<EventRelatedHoliday> eventRelatedHolidays = yearMap.get(year);
        List<EventRelatedHoliday> relatedHolidays = new ArrayList<>(List.copyOf(eventRelatedHolidays));
        relatedHolidays.add(eventRelatedHoliday);
        YearHistory actualYear = getActualYears(year, yearHistories);
        yearHistories.remove(actualYear);
        YearHistory yearHistory = createYearHistoryWithEventRelatedHolidays(relatedHolidays, actualYear);
        yearHistories.add(yearHistory);
        HolidayHistory history = createHolidayHistory(holidayHistory, yearHistories);
        finalSaveHistory(history);
    }

    private Map<Integer, List<EventRelatedHoliday>> getYearMapEventRelatedHolidays(HolidayHistory holidayHistory) {
        return holidayHistory.getYearHistories()
            .stream()
            .collect(Collectors.toMap(YearHistory::getYear, YearHistory::getEventRelatedHolidays));
    }

    private YearHistory getActualYears(int year, List<YearHistory> yearHistories) {
        return yearHistories.stream()
            .filter(yearHistory -> yearHistory.getYear() == year)
            .findFirst()
            .orElse(null);
    }

    private YearHistory createYearHistoryWithEventRelatedHolidays(List<EventRelatedHoliday> relatedHolidays, YearHistory actualYear) {
        return YearHistory.builder()
            .year(actualYear.getYear())
            .eventRelatedHolidays(relatedHolidays)
            .holidayEvents(actualYear.getHolidayEvents())
            .build();
    }

    /**
     * Save history.
     *
     * @param holidayHistory the holiday history
     * @param holidayEvent   the holiday event
     * @param year           the year
     */
    public void saveHistory(HolidayHistory holidayHistory, HolidayEvent holidayEvent, int year) {
        List<YearHistory> yearHistories = new ArrayList<>(List.copyOf(holidayHistory.getYearHistories()));
        Map<Integer, List<HolidayEvent>> yearMap = getYearMapHolidayEvent(holidayHistory);
        List<HolidayEvent> eventRelatedHolidays = expandHolidayEvents(holidayEvent, year, yearMap);
        YearHistory actualYear = getActualYears(year, yearHistories);
        yearHistories.remove(actualYear);
        YearHistory yearHistory = createYearHistoryWithHolidayEvents(eventRelatedHolidays, actualYear);
        yearHistories.add(yearHistory);
        List<YearHistory> orderedByYear = getOrderedByYear(yearHistories);
        HolidayHistory history = createHolidayHistory(holidayHistory, orderedByYear);
        finalSaveHistory(history);
    }

    private void finalSaveHistory(HolidayHistory holidayHistory) {
        service.save(holidayHistory);
    }

    private List<HolidayEvent> expandHolidayEvents(HolidayEvent holidayEvent, int year, Map<Integer, List<HolidayEvent>> yearMap) {
        List<HolidayEvent> eventRelatedHolidays = new ArrayList<>(List.copyOf(yearMap.get(year)));
        eventRelatedHolidays.add(holidayEvent);
        return eventRelatedHolidays;
    }

    private List<YearHistory> getOrderedByYear(List<YearHistory> yearHistories) {
        return yearHistories.stream()
            .sorted(Comparator.comparingInt(YearHistory::getYear))
            .collect(Collectors.toList());
    }

    private HolidayHistory createHolidayHistory(HolidayHistory holidayHistory, List<YearHistory> yearHistories) {
        return HolidayHistory.builder()
            .id(holidayHistory.getId())
            .person(holidayHistory.getPerson())
            .yearHistories(yearHistories)
            .build();
    }

    private Map<Integer, List<HolidayEvent>> getYearMapHolidayEvent(HolidayHistory holidayHistory) {
        return holidayHistory.getYearHistories()
            .stream()
            .collect(Collectors.toMap(YearHistory::getYear, YearHistory::getHolidayEvents));
    }

    private YearHistory createYearHistoryWithHolidayEvents(List<HolidayEvent> relatedHolidays, YearHistory actualYear) {
        return YearHistory.builder()
            .year(actualYear.getYear())
            .eventRelatedHolidays(actualYear.getEventRelatedHolidays())
            .holidayEvents(relatedHolidays)
            .build();
    }

    /**
     * Save history.
     *
     * @param holidayHistory the holiday history
     * @param yearHistory    the year history
     * @param year           the year
     */
    public void saveHistory(HolidayHistory holidayHistory, YearHistory yearHistory, int year) {
        List<YearHistory> yearHistories = new ArrayList<>(List.copyOf(holidayHistory.getYearHistories()));
        YearHistory actualYear = getActualYears(year, yearHistories);
        yearHistories.remove(actualYear);
        yearHistories.add(yearHistory);
        HolidayHistory history = createHolidayHistory(holidayHistory, yearHistories);
        finalSaveHistory(history);
    }

}
