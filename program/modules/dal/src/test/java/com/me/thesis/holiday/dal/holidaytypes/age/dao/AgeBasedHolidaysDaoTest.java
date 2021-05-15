package com.me.thesis.holiday.dal.holidaytypes.age.dao;

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

import com.me.thesis.holiday.dal.holidaytypes.age.domain.AgeBasedHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.age.repository.AgeBasedHolidaysRepository;

/**
 * The type Age based holidays dao test.
 */
class AgeBasedHolidaysDaoTest {

    private static final int AGE = 1;
    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "AGE_HOLIDAY";
    private static final String DESCRIPTION = "days because of user age";
    private static final int DAYS = 20;

    @InjectMocks
    private AgeBasedHolidaysDao underTest;

    @Mock
    private AgeBasedHolidaysRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAgeBasedHolidaysShouldReturnListOfEntities() {
        //GIVEN
        List<AgeBasedHolidaysEntity> expected = createEntities();
        given(repository.findAll()).willReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        List<AgeBasedHolidaysEntity> actual = underTest.getAgeBasedHolidays();
        //THEN
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<AgeBasedHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private AgeBasedHolidaysEntity createEntity() {
        return AgeBasedHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .age(AGE)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveAgeBasedHolidayShouldSave() {
        //GIVEN
        AgeBasedHolidaysEntity holiday = createEntity();

        //WHEN
        verifyNoInteractions(repository);
        underTest.saveAgeBasedHoliday(holiday);
        //THEN
        verify(repository, times(1)).save(holiday);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteAgeBasedHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository);
        underTest.deleteAgeBasedHoliday(HOLIDAY_ID);
        //THEN
        verify(repository, times(1)).deleteById(HOLIDAY_ID);
        verifyNoMoreInteractions(repository);
    }

}
