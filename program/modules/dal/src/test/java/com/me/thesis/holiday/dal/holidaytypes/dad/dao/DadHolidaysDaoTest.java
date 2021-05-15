package com.me.thesis.holiday.dal.holidaytypes.dad.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.dad.domain.DadHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.dad.repository.DadHolidaysRepository;

/**
 * The type Dad holidays dao test.
 */
class DadHolidaysDaoTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "DAD_HOLIDAY";
    private static final String DESCRIPTION = "days because of birth, month limit";
    private static final int LIMIT = 200;
    private static final int DAYS = 20;

    @InjectMocks
    private DadHolidaysDao underTest;

    @Mock
    private DadHolidaysRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDadHolidaysShouldReturnList() {
        //GIVEN
        List<DadHolidaysEntity> expected = createEntities();
        given(repository.findAll()).willReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        List<DadHolidaysEntity> actual = underTest.getAllDadHolidays();
        //THEN
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<DadHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private DadHolidaysEntity createEntity() {
        return DadHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .limit(LIMIT)
            .build();
    }

    @Test
    void testSaveDadHolidayShouldSave() {
        //GIVEN
        DadHolidaysEntity holiday = createEntity();

        //WHEN
        verifyNoInteractions(repository);
        underTest.saveDadHoliday(holiday);
        //THEN
        verify(repository, times(1)).save(holiday);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteDadHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository);
        underTest.deleteDadHoliday(HOLIDAY_ID);
        //THEN
        verify(repository, times(1)).deleteById(HOLIDAY_ID);
        verifyNoMoreInteractions(repository);
    }

}
