package com.me.thesis.holiday.service.holidayhistory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.me.thesis.holiday.domain.holidaytypes.maternity.domain.MaternityHoliday;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.holidaytypes.MaternityHolidaysService;

class MaternityHolidayHistoryServiceTest {

    private static final int BEFORE_DAYS = 10;
    private static final int AFTER_DAYS = 30;
    private static final int YEAR_2021 = 2021;
    private static final int YEAR_2020 = 2020;
    private static final LocalDate EXPECTED_DATE = LocalDate.of(YEAR_2021, 12, 5);
    private static final LocalDate CURRENT_DATE = LocalDate.of(YEAR_2021, 4, 10);

    @InjectMocks
    private MaternityHolidayHistoryService underTest;

    @Mock
    private HolidayHistorySaveService historySaveService;

    @Mock
    private MaternityHolidaysService maternityHolidaysService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddHolidaysShould() {
        //GIVEN
        List<ChildDetails> childDetails = createChildDetails();
        List<MaternityHoliday> maternityHolidays = createMaternityHolidays();
        HolidayHistory holidayHistory = createHolidayHistory();
        EventRelatedHoliday eventRelatedHoliday = createEventRelatedHoliday();
        when(maternityHolidaysService.getAllMaternityHolidays()).thenReturn(maternityHolidays);
        //WHEN
        verifyNoInteractions(historySaveService, maternityHolidaysService);
        underTest.addHolidays(childDetails, CURRENT_DATE);
        //THEN
        verify(maternityHolidaysService).getAllMaternityHolidays();
        verify(historySaveService).saveHistory(holidayHistory, eventRelatedHoliday, CURRENT_DATE.getYear());
        verifyNoMoreInteractions(maternityHolidaysService, historySaveService);
    }

    private EventRelatedHoliday createEventRelatedHoliday() {
        return EventRelatedHoliday.builder()
            .holidayType(EventRelatedHolidayType.MATERNITY)
            .eventDate(EXPECTED_DATE)
            .days(BEFORE_DAYS + AFTER_DAYS)
            .build();
    }

    private HolidayHistory createHolidayHistory() {
        return HolidayHistory.builder()
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
            .holidayEvents(new ArrayList<>())
            .build();
    }

    private List<MaternityHoliday> createMaternityHolidays() {
        return List.of(MaternityHoliday.builder()
            .beforeDays(BEFORE_DAYS)
            .afterDays(AFTER_DAYS)
            .build());
    }

    private List<ChildDetails> createChildDetails() {
        return List.of(createChildDetail());
    }

    private ChildDetails createChildDetail() {
        return ChildDetails.builder()
            .expectedDate(EXPECTED_DATE)
            .person(createPersonDetails())
            .build();
    }

    private PersonDetails createPersonDetails() {
        return PersonDetails.builder()
            .holidayHistory(createHolidayHistory())
            .build();
    }

}
