package com.me.thesis.holiday.service.holidayhistory.calculation;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;
import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;
import com.me.thesis.holiday.dto.holidayplan.HolidayType;
import com.me.thesis.holiday.service.holidayhistory.HolidayHistorySaveService;
import com.me.thesis.holiday.service.lib.EndOfYearProvider;
import com.me.thesis.holiday.service.lib.StartOfYearProvider;
import com.me.thesis.holiday.service.lib.YearProvider;

/**
 * The type Holiday balance calculation test.
 */
class HolidayBalanceCalculatorTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String BASIC_TYPE = "BASIC";
    private static final String DESCRIPTION = "description";
    private static final int DAY = 10;
    private static final int CHILDREN_COUNT = 1;
    private static final int PERSON_AGE = 29;
    private static final HolidayType AGE_HOLIDAY_TYPE = HolidayType.AGE;
    private static final HolidayType CHILD_HOLIDAY_TYPE = HolidayType.CHILD;
    private static final HolidayType CHILD_DISABILITY_HOLIDAY_TYPE = HolidayType.CHILD_DISABILITY;
    private static final HolidayType USER_DISABILITY_HOLIDAY_TYPE = HolidayType.USER_DISABILITY;
    private static final LocalDate EARLIER_START = LocalDate.of(2000, 2, 4);
    private static final LocalDate LATE_START = LocalDate.of(2021, 3, 16);
    private static final LocalDate EARLY_FINISH = LocalDate.of(2021, 6, 15);
    private static final LocalDate NEW_YEAR_DAY = LocalDate.of(2021, 1, 1);
    private static final LocalDate REVOLUTION_DAY = LocalDate.of(2021, 3, 15);
    private static final LocalDate GOOD_FRIDAY = LocalDate.of(2021, 4, 2);
    private static final LocalDate EATER_SUNDAY = LocalDate.of(2021, 4, 4);
    private static final LocalDate END_OF_YEAR = LocalDate.of(2021, 12, 31);
    private static final Year ACTUAL_YEAR = Year.of(2021);
    private static final List<LocalDate> HOLIDAYS = List.of(NEW_YEAR_DAY, REVOLUTION_DAY, GOOD_FRIDAY, EATER_SUNDAY, END_OF_YEAR);
    private static final BigDecimal TEN = BigDecimal.TEN;
    private static final BigDecimal ONE = BigDecimal.ONE;
    private static final BigDecimal FIVE = BigDecimal.valueOf(5);

    @InjectMocks
    private HolidayBalanceCalculator underTest;

    @Mock
    private EndOfYearProvider endOfYearProvider;

    @Mock
    private StartOfYearProvider startOfYearProvider;

    @Mock
    private YearProvider yearProvider;

    @Mock
    private WorkingDaysProvider workingDaysProvider;

    @Mock
    private HolidayHistorySaveService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateUserHolidayBalanceShouldReturnFullYearBalance() {
        //GIVEN
        PersonDetails person = createPersonFull();
        List<YearHistory> yearHistories = List.of(createYearHistory());
        HolidayHistory holidayHistory = createHolidayHistory();
        when(yearProvider.provide()).thenReturn(ACTUAL_YEAR);
        when(startOfYearProvider.provide()).thenReturn(NEW_YEAR_DAY);
        when(endOfYearProvider.provide()).thenReturn(END_OF_YEAR);
        when(workingDaysProvider.provide(NEW_YEAR_DAY, END_OF_YEAR, HOLIDAYS)).thenReturn(TEN);
        //WHEN
        verifyNoInteractions(endOfYearProvider, startOfYearProvider, yearProvider, workingDaysProvider, service);
        underTest.calculateFullUserHolidayBalance(person, HOLIDAYS);
        //THEN
        verify(yearProvider).provide();
        verify(startOfYearProvider).provide();
        verify(endOfYearProvider).provide();
        verify(workingDaysProvider).provide(NEW_YEAR_DAY, END_OF_YEAR, HOLIDAYS);
        verify(service).saveHistory(holidayHistory, yearHistories.get(0), ACTUAL_YEAR.getValue());
        verifyNoMoreInteractions(endOfYearProvider, startOfYearProvider, yearProvider, workingDaysProvider, service);
    }

    private PersonDetails createPersonFull() {
        return PersonDetails.builder()
            .startDate(EARLIER_START)
            .holidayHistory(createHolidayHistory())
            .specificPlan(createSpecificPlan())
            .build();
    }

    private YearHistory createYearHistory() {
        return YearHistory.builder()
            .year(ACTUAL_YEAR.getValue())
            .fixHolidaysForTheYear(ONE)
            .holidayEvents(Collections.emptyList())
            .eventRelatedHolidays(Collections.emptyList())
            .build();
    }

    private BasicHolidays createBasicHoliday() {
        return BasicHolidays.builder()
            .id(HOLIDAY_ID)
            .type(BASIC_TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .build();
    }

    private HolidayHistory createHolidayHistory() {
        return HolidayHistory.builder()
            .id(HOLIDAY_ID)
            .build();
    }

    @Test
    void testCalculateUserHolidayBalanceShouldReturnLateStartBalance() {
        //GIVEN
        PersonDetails person = createPersonLateStart();
        List<YearHistory> yearHistories = List.of(createYearHistory());
        HolidayHistory holidayHistory = createHolidayHistory();
        when(yearProvider.provide()).thenReturn(ACTUAL_YEAR);
        when(yearProvider.provide()).thenReturn(ACTUAL_YEAR);
        when(startOfYearProvider.provide()).thenReturn(NEW_YEAR_DAY);
        when(endOfYearProvider.provide()).thenReturn(END_OF_YEAR);
        when(workingDaysProvider.provide(LATE_START, END_OF_YEAR, HOLIDAYS)).thenReturn(FIVE);
        //WHEN
        verifyNoInteractions(endOfYearProvider, startOfYearProvider, yearProvider, workingDaysProvider, service);
        underTest.calculateFullUserHolidayBalance(person, HOLIDAYS);
        //THEN
        verify(endOfYearProvider).provide();
        verify(yearProvider).provide();
        verify(workingDaysProvider).provide(LATE_START, END_OF_YEAR, HOLIDAYS);
        verify(service).saveHistory(holidayHistory, yearHistories.get(0), ACTUAL_YEAR.getValue());
        verifyNoMoreInteractions(endOfYearProvider, startOfYearProvider, yearProvider, workingDaysProvider, service);
    }

    private PersonDetails createPersonLateStart() {
        return PersonDetails.builder()
            .startDate(LATE_START)
            .holidayHistory(createHolidayHistory())
            .specificPlan(createSpecificPlan())
            .build();
    }

    @Test
    void testCalculateUserHolidayBalanceShouldReturnEarlyFinishBalance() {
        //GIVEN
        PersonDetails person = createPersonEarlyFinish();
        List<YearHistory> yearHistories = List.of(createYearHistory());
        HolidayHistory holidayHistory = createHolidayHistory();
        when(yearProvider.provide()).thenReturn(ACTUAL_YEAR);
        when(yearProvider.provide()).thenReturn(ACTUAL_YEAR);
        when(startOfYearProvider.provide()).thenReturn(NEW_YEAR_DAY);
        when(endOfYearProvider.provide()).thenReturn(END_OF_YEAR);
        when(workingDaysProvider.provide(NEW_YEAR_DAY, EARLY_FINISH, HOLIDAYS)).thenReturn(FIVE);
        //WHEN
        verifyNoInteractions(endOfYearProvider, startOfYearProvider, yearProvider, workingDaysProvider, service);
        underTest.calculateFullUserHolidayBalance(person, HOLIDAYS);
        //THEN
        verify(startOfYearProvider).provide();
        verify(yearProvider).provide();
        verify(workingDaysProvider).provide(NEW_YEAR_DAY, EARLY_FINISH, HOLIDAYS);
        verify(service).saveHistory(holidayHistory, yearHistories.get(0), ACTUAL_YEAR.getValue());
        verifyNoMoreInteractions(endOfYearProvider, startOfYearProvider, yearProvider, workingDaysProvider, service);
    }

    private PersonDetails createPersonEarlyFinish() {
        return PersonDetails.builder()
            .startDate(EARLIER_START)
            .lastDay(EARLY_FINISH)
            .holidayHistory(createHolidayHistory())
            .specificPlan(createSpecificPlan())
            .build();
    }

    private SpecificPlan createSpecificPlan() {
        return SpecificPlan.builder()
            .basicHolidays(createBasicHoliday())
            .ageBasedHolidays(createAgeHoliday())
            .userDisabilityHolidays(createUserDisabilityHoliday())
            .childrenHolidays(createChildrenHoliday())
            .childDisabilityHolidays(createChildDisabilityHoliday())
            .build();
    }

    private AgeBasedHolidays createAgeHoliday() {
        return AgeBasedHolidays.builder()
            .id(HOLIDAY_ID)
            .type(AGE_HOLIDAY_TYPE.getValue())
            .description(DESCRIPTION)
            .age(PERSON_AGE)
            .day(DAY)
            .build();
    }

    private UserDisabilityHolidays createUserDisabilityHoliday() {
        return UserDisabilityHolidays.builder()
            .id(HOLIDAY_ID)
            .type(USER_DISABILITY_HOLIDAY_TYPE.getValue())
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

    private ChildrenHolidays createChildrenHoliday() {
        return ChildrenHolidays.builder()
            .id(HOLIDAY_ID)
            .type(CHILD_HOLIDAY_TYPE.getValue())
            .children(CHILDREN_COUNT)
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

    private ChildDisabilityHolidays createChildDisabilityHoliday() {
        return ChildDisabilityHolidays.builder()
            .id(HOLIDAY_ID)
            .type(CHILD_DISABILITY_HOLIDAY_TYPE.getValue())
            .childrenNumber(CHILDREN_COUNT)
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

}
