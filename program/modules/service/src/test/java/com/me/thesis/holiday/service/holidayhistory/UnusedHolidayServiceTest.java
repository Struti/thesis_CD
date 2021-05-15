package com.me.thesis.holiday.service.holidayhistory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidayhistory.domain.EventRelatedHoliday;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayEvent;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;
import com.me.thesis.holiday.domain.holidaytypes.EventRelatedHolidayType;
import com.me.thesis.holiday.domain.holidaytypes.dad.domain.DadHolidays;
import com.me.thesis.holiday.service.holidaytypes.DadHolidaysService;
import com.me.thesis.holiday.service.lib.CurrentDateProvider;

/**
 * The type Unused holiday service test.
 */
class UnusedHolidayServiceTest {

    private static final LocalDate CURRENT_DATE = LocalDate.of(2021, 4, 5);
    private static final LocalDate EXPIRATION = CURRENT_DATE.minusDays(4);
    private static final int LIMIT = 3;
    private static final int DAY = 3;
    private static final long HISTORY_ID_1 = 1111L;
    private static final long HISTORY_ID_2 = 2222L;
    private static final long HISTORY_ID_3 = 3333L;
    private static final long HISTORY_ID_4 = 4444L;
    private static final long HISTORY_ID_5 = 5555L;
    private static final int YEAR_2021 = 2021;
    private static final int YEAR_2020 = 2020;
    private static final EventRelatedHolidayType DAD = EventRelatedHolidayType.DAD;
    private static final EventRelatedHolidayType DEATH = EventRelatedHolidayType.DEATH;
    private static final LocalDate COMMON_START_1 = LocalDate.of(2021, 4, 3);
    private static final LocalDate COMMON_END = LocalDate.of(2021, 4, 4);
    private static final LocalDate FULL_IN_ONE_START = LocalDate.of(2021, 3, 15);
    private static final LocalDate FULL_IN_ONE_END = LocalDate.of(2021, 3, 18);
    private static final LocalDate START = LocalDate.of(2021, 4, 1);
    private static final int ONE = 1;
    private static final int TWO = 2;

    @InjectMocks
    private UnusedHolidayService underTest;

    @Mock
    private CurrentDateProvider currentDateProvider;

    @Mock
    private HolidayHistoryService holidayHistoryService;

    @Mock
    private DadHolidaysService dadHolidaysService;

    @Mock
    private HolidayHistorySaveService historySaveService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRemoveShouldNotFail() {
        //GIVEN
        List<HolidayHistory> emptyList = Collections.emptyList();
        when(holidayHistoryService.getDadsHistories()).thenReturn(emptyList);
        //WHEN
        verifyNoInteractions(currentDateProvider, holidayHistoryService, dadHolidaysService, historySaveService);
        underTest.addPenalty();
        //THEN
        verify(holidayHistoryService).getDadsHistories();
        verifyNoMoreInteractions(currentDateProvider, holidayHistoryService, dadHolidaysService, historySaveService);
    }

    @Test
    void testRemoveShouldRemove() {
        //GIVEN
        List<HolidayHistory> dadsHistories = createDadsHistories();
        HolidayHistory dadsHistory = createDadsHistory(HISTORY_ID_2, DAD, EXPIRATION, true, START, START.plusDays(ONE));
        EventRelatedHoliday penalty = createPenalty(-1);
        when(holidayHistoryService.getDadsHistories()).thenReturn(dadsHistories);
        when(currentDateProvider.provide()).thenReturn(CURRENT_DATE);
        when(dadHolidaysService.getAllDadHolidays()).thenReturn(createDadHolidays());
        when(holidayHistoryService.getHistory(HISTORY_ID_2)).thenReturn(dadsHistories.get(ONE));
        //WHEN
        verifyNoInteractions(currentDateProvider, holidayHistoryService, dadHolidaysService, historySaveService);
        underTest.addPenalty();
        //THEN
        verify(holidayHistoryService).getDadsHistories();
        verify(currentDateProvider).provide();
        verify(dadHolidaysService).getAllDadHolidays();
        verify(holidayHistoryService).getHistory(HISTORY_ID_2);
        verify(historySaveService).saveHistory(dadsHistory, penalty, CURRENT_DATE.getYear());
        verifyNoMoreInteractions(currentDateProvider, holidayHistoryService, dadHolidaysService, historySaveService);
    }

    private List<HolidayHistory> createDadsHistories() {
        return List.of(createDadsHistory(HISTORY_ID_1, DAD, CURRENT_DATE, false, FULL_IN_ONE_START, FULL_IN_ONE_END),
            createDadsHistory(HISTORY_ID_2, DAD, EXPIRATION, true, START, START.plusDays(ONE)),
            createDadsHistory(HISTORY_ID_3, DAD, EXPIRATION, true, START, START.plusDays(TWO)),
            createDadsHistory(HISTORY_ID_4, DEATH, CURRENT_DATE, false, FULL_IN_ONE_START, FULL_IN_ONE_END),
            createDadsHistory());
    }

    private List<DadHolidays> createDadHolidays() {
        return List.of(DadHolidays.builder()
            .limit(LIMIT)
            .day(DAY)
            .build());
    }

    private HolidayHistory createDadsHistory(long id, EventRelatedHolidayType type, LocalDate expiration, boolean dadHoliday, LocalDate start, LocalDate end) {
        return HolidayHistory.builder()
            .id(id)
            .yearHistories(createYearHistories(type, expiration, dadHoliday, start, end))
            .build();
    }

    private HolidayHistory createDadsHistory() {
        return HolidayHistory.builder()
            .id(HISTORY_ID_5)
            .yearHistories(createYearHistories())
            .build();
    }

    private List<YearHistory> createYearHistories(EventRelatedHolidayType type, LocalDate expiration, boolean dadHoliday, LocalDate start, LocalDate end) {
        return List.of(createYearHistory(YEAR_2020, type, expiration, dadHoliday, start, end), createYearHistory(YEAR_2021, type, expiration, dadHoliday, start, end));
    }

    private List<YearHistory> createYearHistories() {
        return List.of(createYearHistory());
    }

    private YearHistory createYearHistory(int year, EventRelatedHolidayType type, LocalDate expiration, boolean dadHoliday, LocalDate start, LocalDate end) {
        return YearHistory.builder()
            .year(year)
            .eventRelatedHolidays(createEventRelatedHolidays(type, expiration))
            .holidayEvents(List.of(createHolidayEvent(true, COMMON_START_1, COMMON_END), createHolidayEvent(false, COMMON_START_1, COMMON_END),
                createHolidayEvent(dadHoliday, start, end)))
            .build();
    }

    private YearHistory createYearHistory() {
        return YearHistory.builder()
            .year(YEAR_2021)
            .eventRelatedHolidays(createEventRelatedHolidays())
            .holidayEvents(List.of(createHolidayEvent()))
            .build();
    }

    private List<EventRelatedHoliday> createEventRelatedHolidays(EventRelatedHolidayType type, LocalDate expiration) {
        return List.of(createEventRelatedHoliday(type, expiration));
    }

    private HolidayEvent createHolidayEvent(boolean dadHoliday, LocalDate start, LocalDate end) {
        return HolidayEvent.builder()
            .dadHoliday(dadHoliday)
            .startDate(start)
            .endDate(end)
            .build();
    }

    private List<EventRelatedHoliday> createEventRelatedHolidays() {
        return List.of(createEventRelatedHoliday());
    }

    private HolidayEvent createHolidayEvent() {
        return HolidayEvent.builder()
            .dadHoliday(true)
            .startDate(FULL_IN_ONE_START)
            .endDate(FULL_IN_ONE_END)
            .build();
    }

    private EventRelatedHoliday createEventRelatedHoliday(EventRelatedHolidayType type, LocalDate expiration) {
        return EventRelatedHoliday.builder()
            .holidayType(type)
            .expiration(expiration)
            .build();
    }

    private EventRelatedHoliday createEventRelatedHoliday() {
        return EventRelatedHoliday.builder()
            .holidayType(EventRelatedHolidayType.DAD)
            .expiration(EXPIRATION)
            .build();
    }

    private EventRelatedHoliday createPenalty(int penalty) {
        return EventRelatedHoliday.builder()
            .holidayType(EventRelatedHolidayType.DAD_PENALTY)
            .days(penalty)
            .eventDate(CURRENT_DATE)
            .build();
    }

}
