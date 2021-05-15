package com.me.thesis.holiday.service.holidayhistory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.domain.holidayhistory.domain.EventRelatedHoliday;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayEvent;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;
import com.me.thesis.holiday.domain.holidaytypes.EventRelatedHolidayType;
import com.me.thesis.holiday.domain.holidaytypes.dad.domain.DadHolidays;
import com.me.thesis.holiday.service.holidaytypes.DadHolidaysService;
import com.me.thesis.holiday.service.lib.CurrentDateProvider;

/**
 * The type Unused holiday service.
 */
@Service
public class UnusedHolidayService {

    private static final int ZERO = 0;

    @Autowired
    private CurrentDateProvider currentDateProvider;

    @Autowired
    private HolidayHistoryService holidayHistoryService;

    @Autowired
    private DadHolidaysService dadHolidaysService;

    @Autowired
    private HolidayHistorySaveService historySaveService;

    /**
     * Add penalty.
     */
    public void addPenalty() {
        List<HolidayHistory> dadHistories = getHistories();
        if (!dadHistories.isEmpty()) {
            DadHolidays dadHolidays = getDadHolidays();
            LocalDate currentDate = getCurrentDate();
            Map<Long, Long> dadsInTheCurrentYear = getDadsInTheCurrentYear(dadHistories, currentDate);
            List<HolidayHistory> freshDads = getFreshDads(dadHistories, dadsInTheCurrentYear);
            Map<Long, Long> expiredHolidays = getExpiredHolidays(currentDate, freshDads);
            List<HolidayHistory> expired = getExpired(dadHistories, expiredHolidays);
            if (!expired.isEmpty()) {
                Map<Long, Long> unusedDadHolidaysMap = getUsedDadHolidays(currentDate, dadHolidays, expired);
                unusedDadHolidaysMap
                    .forEach((historyId, count) -> calculateAndSavePenalty(currentDate, dadHolidays, historyId, count));
            }
        }

    }

    private Map<Long, Long> getDadsInTheCurrentYear(List<HolidayHistory> dadHistories, LocalDate currentDate) {
        Map<Long, Long> dadsInTheCurrentYear = dadHistories.stream()
            .collect(Collectors.toMap(HolidayHistory::getId, holidayHistory -> holidayHistory.getYearHistories()
                .stream()
                .filter(yearHistory -> isActual(currentDate, yearHistory))
                .map(YearHistory::getEventRelatedHolidays)
                .flatMap(Collection::stream)
                .filter(this::isRecentlyDad)
                .count()));
        removeDadsWithoutNewBorn(dadsInTheCurrentYear);
        return dadsInTheCurrentYear;
    }

    private List<HolidayHistory> getFreshDads(List<HolidayHistory> dadHistories, Map<Long, Long> dadsInTheCurrentYear) {
        return dadHistories.stream()
            .filter(holidayHistory -> dadsInTheCurrentYear.containsKey(holidayHistory.getId()))
            .collect(Collectors.toList());
    }

    private Map<Long, Long> getExpiredHolidays(LocalDate currentDate, List<HolidayHistory> dadHistories) {
        Map<Long, Long> expiredHolidays = dadHistories.stream()
            .collect(Collectors.toMap(HolidayHistory::getId, holidayHistory ->
                getExpiredCount(currentDate, holidayHistory)));
        expiredHolidays.values()
            .removeIf(this::isZeroCount);
        return expiredHolidays;
    }

    private List<HolidayHistory> getExpired(List<HolidayHistory> dadHistories, Map<Long, Long> expiredHolidays) {
        return dadHistories.stream()
            .filter(holidayHistory -> expiredHolidays.containsKey(holidayHistory.getId()))
            .collect(Collectors.toList());
    }

    private Map<Long, Long> getUsedDadHolidays(LocalDate currentDate, DadHolidays dadHolidays, List<HolidayHistory> expired) {
        Map<Long, Long> usedHolidaysCount = expired.stream()
            .collect(Collectors.toMap(HolidayHistory::getId, holidayHistory ->
                getUsedDadHolidays(currentDate, dadHolidays, holidayHistory)));
        usedHolidaysCount.values()
            .removeIf(this::isZeroCount);
        return usedHolidaysCount;
    }

    private void calculateAndSavePenalty(LocalDate currentDate, DadHolidays dadHolidays, Long historyId, Long count) {
        int dadHolidaysDay = dadHolidays.getDay();
        if (count < dadHolidays.getDay()) {
            int penalty = Math.toIntExact(count - dadHolidaysDay);
            HolidayHistory history = holidayHistoryService.getHistory(historyId);
            EventRelatedHoliday penaltyEntry = createPenalty(penalty, currentDate);
            historySaveService.saveHistory(history, penaltyEntry, currentDate.getYear());
        }
    }

    private boolean isRecentlyDad(EventRelatedHoliday eventRelatedHoliday) {
        return eventRelatedHoliday.getHolidayType()
            .equals(EventRelatedHolidayType.DAD);
    }

    private void removeDadsWithoutNewBorn(Map<Long, Long> dadsInTheCurrentYear) {
        dadsInTheCurrentYear.values()
            .removeIf(this::isZeroCount);
    }

    private List<HolidayHistory> getHistories() {
        return holidayHistoryService.getDadsHistories();
    }

    private LocalDate getCurrentDate() {
        return currentDateProvider.provide();
    }

    private DadHolidays getDadHolidays() {
        return dadHolidaysService.getAllDadHolidays()
            .stream()
            .findFirst()
            .orElse(null);
    }

    private long getExpiredCount(LocalDate currentDate, HolidayHistory holidayHistory) {
        return holidayHistory.getYearHistories()
            .stream()
            .filter(Objects::nonNull)
            .filter(yearHistory -> isActual(currentDate, yearHistory))
            .map(YearHistory::getEventRelatedHolidays)
            .flatMap(Collection::stream)
            .map(EventRelatedHoliday::getExpiration)
            .filter(Objects::nonNull)
            .flatMap(Optional::stream)
            .filter(date -> date.isBefore(currentDate))
            .count();
    }

    private boolean isZeroCount(Long count) {
        return count == ZERO;
    }

    private Long getUsedDadHolidays(LocalDate currentDate, DadHolidays dadHolidays, HolidayHistory holidayHistory) {
        return holidayHistory.getYearHistories()
            .stream()
            .filter(yearHistory -> isActual(currentDate, yearHistory))
            .map(YearHistory::getHolidayEvents)
            .flatMap(Collection::stream)
            .filter(this::getDadHoliday)
            .filter(holidayEvent -> isInTheRange(currentDate, dadHolidays, holidayEvent))
            .map(holidayEvent -> ChronoUnit.DAYS.between(holidayEvent.getStartDate(), holidayEvent.getEndDate()))
            .filter(day -> day < dadHolidays.getDay())
            .reduce(Long::sum)
            .orElse(0L);
    }

    private Boolean getDadHoliday(HolidayEvent holidayEvent) {
        return holidayEvent.isDadHoliday()
            .orElse(false);
    }

    private EventRelatedHoliday createPenalty(int penalty, LocalDate currentDate) {
        return EventRelatedHoliday.builder()
            .days(penalty)
            .holidayType(EventRelatedHolidayType.DAD_PENALTY)
            .eventDate(currentDate)
            .build();
    }

    private boolean isActual(LocalDate currentDate, YearHistory yearHistory) {
        return yearHistory.getYear() == currentDate.getYear();
    }

    private boolean isInTheRange(LocalDate currentDate, DadHolidays dadHolidays, HolidayEvent holidayEvent) {
        return holidayEvent.getStartDate()
            .isBefore(currentDate) || holidayEvent.getStartDate()
            .isAfter(currentDate.minusMonths(dadHolidays.getLimit()));
    }

}
