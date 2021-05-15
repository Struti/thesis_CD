package com.me.thesis.holiday.service.holidayhistory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.holidayhistory.domain.EventRelatedHoliday;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;
import com.me.thesis.holiday.domain.holidaytypes.EventRelatedHolidayType;
import com.me.thesis.holiday.domain.holidaytypes.dad.domain.DadHolidays;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.holidaytypes.DadHolidaysService;

class DadHolidaysHistoryServiceTest {

    private static final int YEAR_2021 = 2021;
    private static final LocalDate CURRENT_DATE = LocalDate.of(YEAR_2021, 4, 5);
    private static final int SUBTRACT = 1;
    private static final int DAY = 3;
    private static final long HISTORY_ID_1 = 111L;
    private static final long HISTORY_ID_2 = 222L;
    private static final long HISTORY_ID_3 = 333L;
    private static final int YEAR_2020 = 2020;

    @InjectMocks
    private DadHolidaysHistoryService underTest;

    @Mock
    private DadHolidaysService dadHolidaysService;

    @Mock
    private HolidayHistoryService holidayHistoryService;

    @Mock
    private HolidayHistorySaveService historySaveService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddHolidaysShouldNotFailWithEmptyList() {
        //GIVEN
        List<ChildDetails> emptyList = Collections.emptyList();
        //WHEN
        verifyNoInteractions(dadHolidaysService, holidayHistoryService, historySaveService);
        underTest.addHolidays(emptyList, CURRENT_DATE);
        //THEN
        verifyNoMoreInteractions(dadHolidaysService, holidayHistoryService, historySaveService);
    }

    @Test
    void testAddHolidaysShouldSave() {
        //GIVEN
        List<ChildDetails> childDetails = createChildDetails();
        List<DadHolidays> dadHolidays = createDadHolidays();
        HolidayHistory history_1 = createHistory(HISTORY_ID_1);
        HolidayHistory history_2 = createHistory(HISTORY_ID_2);
        EventRelatedHoliday eventRelatedHoliday_1 = createEventRelatedHoliday(CURRENT_DATE);
        EventRelatedHoliday eventRelatedHoliday_2 = createEventRelatedHoliday(CURRENT_DATE.minusDays(SUBTRACT));
        when(dadHolidaysService.getAllDadHolidays()).thenReturn(dadHolidays);
        when(holidayHistoryService.getHistory(HISTORY_ID_1)).thenReturn(history_1);
        when(holidayHistoryService.getHistory(HISTORY_ID_2)).thenReturn(history_2);
        //WHEN
        verifyNoInteractions(dadHolidaysService, holidayHistoryService, historySaveService);
        underTest.addHolidays(childDetails, CURRENT_DATE);
        //THEN
        verify(dadHolidaysService).getAllDadHolidays();
        verify(holidayHistoryService).getHistory(HISTORY_ID_1);
        verify(historySaveService).saveHistory(history_1, eventRelatedHoliday_1, CURRENT_DATE.getYear());
        verify(holidayHistoryService).getHistory(HISTORY_ID_2);
        verify(historySaveService).saveHistory(history_2, eventRelatedHoliday_2, CURRENT_DATE.getYear());
        verifyNoMoreInteractions(dadHolidaysService, holidayHistoryService, historySaveService);
    }

    private List<DadHolidays> createDadHolidays() {
        return List.of(createDadHoliday());
    }

    private DadHolidays createDadHoliday() {
        return DadHolidays.builder()
            .day(DAY)
            .limit(DAY)
            .build();
    }

    private List<ChildDetails> createChildDetails() {
        return List.of(createChild(CURRENT_DATE, HISTORY_ID_1), createChild(CURRENT_DATE.minusDays(SUBTRACT), HISTORY_ID_2), createChild(CURRENT_DATE.minusYears(SUBTRACT),
            HISTORY_ID_3));
    }

    private ChildDetails createChild(LocalDate bornDate, Long historyId) {
        return ChildDetails.builder()
            .birthDate(bornDate)
            .person(createPerson(historyId))
            .build();
    }

    private PersonDetails createPerson(long historyId) {
        return PersonDetails.builder()
            .holidayHistory(createHistory(historyId))
            .build();
    }

    private HolidayHistory createHistory(long historyId) {
        return HolidayHistory.builder()
            .id(historyId)
            .yearHistories(createYearHistories())
            .build();
    }

    private List<YearHistory> createYearHistories() {
        return List.of(createYearHistory(YEAR_2020), createYearHistory(YEAR_2021));
    }

    private YearHistory createYearHistory(int year) {
        return YearHistory.builder()
            .year(year)
            .eventRelatedHolidays(new ArrayList<>())
            .build();
    }

    private EventRelatedHoliday createEventRelatedHoliday(LocalDate event) {
        return EventRelatedHoliday.builder()
            .holidayType(EventRelatedHolidayType.DAD)
            .days(3)
            .eventDate(event)
            .expiration(event.plusMonths(3))
            .build();
    }

}
