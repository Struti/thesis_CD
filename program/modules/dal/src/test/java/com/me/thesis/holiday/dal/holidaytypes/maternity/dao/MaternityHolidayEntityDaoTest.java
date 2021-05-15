package com.me.thesis.holiday.dal.holidaytypes.maternity.dao;

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

import com.me.thesis.holiday.dal.holidaytypes.maternity.domain.MaternityHolidayEntity;
import com.me.thesis.holiday.dal.holidaytypes.maternity.repository.MaternityHolidayRepository;

/**
 * The type Maternity holiday entity dao test.
 */
class MaternityHolidayEntityDaoTest {

    private static final long HOLIDAY_ID_1 = 11111L;
    private static final String TYPE = "MOM_GIVE_BIRTH_HOLIDAY";
    private static final String DAYS_BEFORE_EXPECTED_DATE_DESCRIPTION = "days before expected date";
    private static final int DAYS_BEFORE_EXPECTED_DATE = 20;
    private static final long HOLIDAY_ID_2 = 22222L;
    private static final String DAYS_AFTER_EXPECTED_DATE_DESCRIPTION = "days after expected date";
    private static final int DAYS_AFTER_EXPECTED_DATE = 200;

    @InjectMocks
    private MaternityHolidayDao underTest;

    @Mock
    private MaternityHolidayRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMaternityHolidaysShouldReturnHolidays() {
        //GIVEN
        List<MaternityHolidayEntity> expected = createEntities();
        given(repository.findAll()).willReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        List<MaternityHolidayEntity> actual = underTest.getAllMaternityHolidays();
        //THEN
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<MaternityHolidayEntity> createEntities() {
        return List.of(createHolidayBeforeExpectedDate(), createHolidayAfterExpectedDate());
    }

    private MaternityHolidayEntity createHolidayBeforeExpectedDate() {
        return MaternityHolidayEntity.builder()
            .id(HOLIDAY_ID_1)
            .type(TYPE)
            .description(DAYS_BEFORE_EXPECTED_DATE_DESCRIPTION)
            .beforeDays(DAYS_BEFORE_EXPECTED_DATE)
            .afterDays(DAYS_AFTER_EXPECTED_DATE)
            .build();
    }

    private MaternityHolidayEntity createHolidayAfterExpectedDate() {
        return MaternityHolidayEntity.builder()
            .id(HOLIDAY_ID_2)
            .type(TYPE)
            .description(DAYS_AFTER_EXPECTED_DATE_DESCRIPTION)
            .beforeDays(DAYS_BEFORE_EXPECTED_DATE)
            .afterDays(DAYS_AFTER_EXPECTED_DATE)
            .build();
    }

    @Test
    void testSaveMaternityHolidayShouldSave() {
        //GIVEN
        MaternityHolidayEntity holiday = createHolidayBeforeExpectedDate();

        //WHEN
        verifyNoInteractions(repository);
        underTest.saveMaternityHoliday(holiday);
        //THEN
        verify(repository, times(1)).save(holiday);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteMaternityHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository);
        underTest.deleteMaternityHoliday(HOLIDAY_ID_1);
        //THEN
        verify(repository, times(1)).deleteById(HOLIDAY_ID_1);
        verifyNoMoreInteractions(repository);
    }

}
