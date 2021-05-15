package com.me.thesis.holiday.service.holidayhistory.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import com.me.thesis.holiday.service.holidayhistory.HolidayHistoryService;
import com.me.thesis.holiday.service.lib.CurrentDateProvider;

class AvailableHolidaysCalculatorTest {

    private static final long ID = 11111L;
    private static final int YEAR_2020 = 2020;
    private static final int YEAR_2021 = 2021;
    private static final int DAYS = 10;
    private static final int ONE = 1;
    private static final LocalDate START_DATE = LocalDate.of(2021, ONE, ONE);
    private static final LocalDate END_DATE = LocalDate.of(2021, ONE, 11);
    private static final BigDecimal EXPECTED = BigDecimal.TEN;

    @InjectMocks
    private AvailableHolidaysCalculator underTest;

    @Mock
    private HolidayHistoryService holidayHistoryService;

    @Mock
    private CurrentDateProvider currentDateProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateAvailableHolidaysShould() {
        //GIVEN
        HolidayHistory holidayHistory = createHolidayHistory();
        when(holidayHistoryService.getHistoryByPersonId(ID)).thenReturn(holidayHistory);
        when(currentDateProvider.provide()).thenReturn(END_DATE);
        //WHEN
        verifyNoInteractions(holidayHistoryService, currentDateProvider);
        BigDecimal actual = underTest.calculateAvailableHolidays(ID);
        //THEN
        verify(holidayHistoryService).getHistoryByPersonId(ID);
        verify(currentDateProvider).provide();
        verifyNoMoreInteractions(holidayHistoryService, currentDateProvider);
        assertEquals(EXPECTED, actual);
    }

    private HolidayHistory createHolidayHistory() {
        return HolidayHistory.builder()
            .id(ID)
            .yearHistories(createYearHistories())
            .build();
    }

    private List<YearHistory> createYearHistories() {
        return List.of(createYearHistory(YEAR_2020), createYearHistory(YEAR_2021));
    }

    private YearHistory createYearHistory(int year) {
        return YearHistory.builder()
            .year(year)
            .fixHolidaysForTheYear(BigDecimal.TEN)
            .eventRelatedHolidays(createEventRelatedHolidays())
            .holidayEvents(createHolidayEvents())
            .build();
    }

    private List<EventRelatedHoliday> createEventRelatedHolidays() {
        return List.of(createEventRelatedHoliday(), createEventRelatedHoliday());
    }

    private List<HolidayEvent> createHolidayEvents() {
        return List.of(createHolidayEvent(), createHolidayEvent());
    }

    private EventRelatedHoliday createEventRelatedHoliday() {
        return EventRelatedHoliday.builder()
            .eventDate(LocalDate.EPOCH)
            .days(DAYS)
            .build();
    }

    private HolidayEvent createHolidayEvent() {
        return HolidayEvent.builder()
            .startDate(START_DATE)
            .endDate(END_DATE)
            .build();
    }

}
