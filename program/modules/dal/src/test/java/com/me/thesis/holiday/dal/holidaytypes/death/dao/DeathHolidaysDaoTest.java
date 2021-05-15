package com.me.thesis.holiday.dal.holidaytypes.death.dao;

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

import com.me.thesis.holiday.dal.holidaytypes.death.domain.DeathHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.death.repository.DeathHolidaysRepository;

/**
 * The type Death holidays dao test.
 */
class DeathHolidaysDaoTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "DEATH_HOLIDAY";
    private static final String DESCRIPTION = "death cause holidays";
    private static final int DAYS = 20;

    @InjectMocks
    private DeathHolidaysDao underTest;

    @Mock
    private DeathHolidaysRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDeathHolidaysShouldReturnList() {
        //GIVEN
        List<DeathHolidaysEntity> expected = createEntities();
        given(repository.findAll()).willReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        List<DeathHolidaysEntity> actual = underTest.getDeathHolidays();
        //THEN
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<DeathHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private DeathHolidaysEntity createEntity() {
        return DeathHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveDeathHolidayShouldSave() {
        //GIVEN
        DeathHolidaysEntity holiday = createEntity();

        //WHEN
        verifyNoInteractions(repository);
        underTest.saveDeathHoliday(holiday);
        //THEN
        verify(repository, times(1)).save(holiday);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteDeathHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository);
        underTest.deleteDeathHoliday(HOLIDAY_ID);
        //THEN
        verify(repository, times(1)).deleteById(HOLIDAY_ID);
        verifyNoMoreInteractions(repository);
    }

}
