package com.me.thesis.holiday.service.nationalholiday;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.me.thesis.holiday.dal.nationalholiday.dao.NationalHolidayDao;
import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;

class NationalHolidayServiceTest {

    private static final int MARCH = 3;
    private static final int FIFTEEN = 15;
    private static final long ID = 1111L;
    private static final int YEAR = 2021;
    private static final LocalDate REVOLUTION_DAY = LocalDate.of(YEAR, 3, 15);
    private static final LocalDate WHIT_MONDAY = LocalDate.of(YEAR, 4, 5);

    @InjectMocks
    private NationalHolidayService underTest;

    @Mock
    private NationalHolidayDao dao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNationalHolidaysShouldReturnList() {
        //GIVEN
        List<NationalHoliday> expected = createNationalHolidays();
        when(dao.getNationalHolidays()).thenReturn(expected);
        //WHEN
        verifyNoInteractions(dao);
        List<NationalHoliday> actual = underTest.getNationalHolidays();
        //THEN
        verify(dao).getNationalHolidays();
        verifyNoMoreInteractions(dao);
        assertEquals(expected, actual);
    }

    private List<NationalHoliday> createNationalHolidays() {
        return List.of(createNationalHoliday(REVOLUTION_DAY, true), createNationalHoliday(WHIT_MONDAY, false));
    }

    private NationalHoliday createNationalHoliday(LocalDate date, boolean fix) {
        return NationalHoliday.builder()
            .id(ID)
            .date(date)
            .fix(fix)
            .build();
    }

    @Test
    void testSaveNationalHolidayShouldSaveEntity() {
        //GIVEN
        NationalHoliday nationalHoliday = createNationalHoliday(REVOLUTION_DAY, true);
        List<NationalHoliday> nationalHolidays = createNationalHolidays();
        List<NationalHoliday> next10YearsHolidays = createNext10YearsHolidays();
        when(dao.getNationalHolidays()).thenReturn(nationalHolidays);
        //WHEN
        underTest.saveNationalHoliday(nationalHoliday);
        //THEN
        verify(dao).saveNationalHoliday(nationalHoliday);
        verify(dao).getNationalHolidays();
        verify(dao).saveNationalHolidays(next10YearsHolidays);
        verifyNoMoreInteractions(dao);
    }

    private List<NationalHoliday> createNext10YearsHolidays() {
        List<NationalHoliday> nationalHolidays = new ArrayList<>();
        int year = YEAR;
        for (int i = 0; i < 9; i++) {
            NationalHoliday nationalHoliday = createNationalHolidayNoId(LocalDate.of(++year, MARCH, FIFTEEN));
            nationalHolidays.add(nationalHoliday);
        }
        return nationalHolidays;
    }

    private NationalHoliday createNationalHolidayNoId(LocalDate date) {
        return NationalHoliday.builder()
            .date(date)
            .fix(true)
            .build();
    }

    @Test
    void testAddFixForNextTenYearShould() {
        //GIVEN
        List<NationalHoliday> nationalHolidays = createNationalHolidays();
        List<NationalHoliday> next10YearsHolidays = createNext10YearsHolidays();
        when(dao.getNationalHolidays()).thenReturn(nationalHolidays);
        //WHEN
        underTest.addFixForNextTenYear();
        //THEN
        verify(dao).getNationalHolidays();
        verify(dao).saveNationalHolidays(next10YearsHolidays);
        verifyNoMoreInteractions(dao);
    }

    @Test
    void testSaveNationalHolidaysShould() {
        //GIVEN
        List<NationalHoliday> nationalHolidays = createNationalHolidays();
        //WHEN
        verifyNoInteractions(dao);
        underTest.saveNationalHolidays(nationalHolidays);
        //THEN
        verify(dao).saveNationalHolidays(nationalHolidays);
        verifyNoMoreInteractions(dao);
    }

    @Test
    void testDeleteNationalHolidayShould() {
        //GIVEN
        //WHEN
        verifyNoInteractions(dao);
        underTest.deleteNationalHoliday(ID);
        //THEN
        verify(dao).deleteNationalHoliday(ID);
        verifyNoMoreInteractions(dao);
    }

    @Test
    void testDeleteEarlierNationalHolidaysShould() {
        //GIVEN
        //WHEN
        verifyNoInteractions(dao);
        underTest.deleteEarlierNationalHolidays(REVOLUTION_DAY);
        //THEN
        verify(dao).deleteEarlierNationalHolidays(REVOLUTION_DAY);
        verifyNoMoreInteractions(dao);
    }

}
