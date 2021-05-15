package com.me.thesis.holiday.service.holidayhistory.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * The type Working days provider test.
 */
class WorkingDaysProviderTest {

    private static final BigDecimal FULL_YEAR_WORKING_DAYS = BigDecimal.valueOf(259);
    private static final BigDecimal LATE_START_WORKING_DAYS = BigDecimal.valueOf(188);
    private static final BigDecimal EARLY_FINISH_WORKING_DAYS = BigDecimal.valueOf(114);
    private static final int YEAR = 2021;
    private static final int TEN = 10;
    private static final int ONE = 1;
    private static final LocalDate FIRST_DAY_OF_YEAR = LocalDate.of(YEAR, ONE, ONE);
    private static final LocalDate LAST_DAY_OF_YEAR = LocalDate.of(YEAR, 12, 31);
    private static final LocalDate START_DAY = LocalDate.of(YEAR, 4, TEN);
    private static final LocalDate END_DAY = LocalDate.of(YEAR, 6, TEN);
    private static final LocalDate MAY_ONE = LocalDate.of(YEAR, 5, ONE);
    private static final LocalDate AUGUST_TWENTY = LocalDate.of(YEAR, 8, 20);

    @InjectMocks
    private WorkingDaysProvider underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProvideShouldCalculateFullYear() {
        //GIVEN
        List<LocalDate> holidays = createHolidays();
        //WHEN
        BigDecimal actual = underTest.provide(FIRST_DAY_OF_YEAR, LAST_DAY_OF_YEAR, holidays);
        //THEN
        assertEquals(FULL_YEAR_WORKING_DAYS, actual);
    }

    private List<LocalDate> createHolidays() {
        return List.of(MAY_ONE, AUGUST_TWENTY);
    }

    @Test
    void testProvideShouldCalculatePartialYearLateStart() {
        //GIVEN
        List<LocalDate> holidays = createHolidays();
        //WHEN
        BigDecimal actual = underTest.provide(START_DAY, LAST_DAY_OF_YEAR, holidays);
        //THEN
        assertEquals(LATE_START_WORKING_DAYS, actual);
    }

    @Test
    void testProvideShouldCalculatePartialYearEarlyFinish() {
        //GIVEN
        List<LocalDate> holidays = createHolidays();
        //WHEN
        BigDecimal actual = underTest.provide(FIRST_DAY_OF_YEAR, END_DAY, holidays);
        //THEN
        assertEquals(EARLY_FINISH_WORKING_DAYS, actual);
    }

}
