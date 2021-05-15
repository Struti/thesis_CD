package com.me.thesis.holiday.dal.holidaytypes.basic.dao;

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

import com.me.thesis.holiday.dal.holidaytypes.basic.domain.BasicHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.basic.repository.BasicHolidaysRepository;

/**
 * The type Basic holidays dao test.
 */
class BasicHolidaysDaoTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "BASIC_HOLIDAY";
    private static final String DESCRIPTION = "basic holidays";
    private static final int DAYS = 20;

    @InjectMocks
    private BasicHolidaysDao underTest;

    @Mock
    private BasicHolidaysRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicHolidaysShouldReturnList() {
        //GIVEN
        List<BasicHolidaysEntity> expected = createEntities();
        given(repository.findAll()).willReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        List<BasicHolidaysEntity> actual = underTest.getBasicHolidays();
        //THEN
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<BasicHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private BasicHolidaysEntity createEntity() {
        return BasicHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveBasicHolidayShouldSave() {
        //GIVEN
        BasicHolidaysEntity holiday = createEntity();

        //WHEN
        verifyNoInteractions(repository);
        underTest.saveBasicHoliday(holiday);
        //THEN
        verify(repository, times(1)).save(holiday);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteBasicHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository);
        underTest.deleteBasicHoliday(HOLIDAY_ID);
        //THEN
        verify(repository, times(1)).deleteById(HOLIDAY_ID);
        verifyNoMoreInteractions(repository);
    }

}
