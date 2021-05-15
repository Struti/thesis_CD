package com.me.thesis.holiday.dal.holidaytypes.childdisability.dao;

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

import com.me.thesis.holiday.dal.holidaytypes.childdisability.domain.ChildDisabilityHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.childdisability.repository.ChildDisabilityHolidayRepository;

/**
 * The type Default child disability holiday dao test.
 */
class DefaultChildDisabilityHolidayDaoTest {

    private static final int CHILDREN_NUMBER = 1;
    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "CHILD_DISABILITY_HOLIDAY";
    private static final String DESCRIPTION = "days because of 1 child disability";
    private static final int DAYS = 20;

    @InjectMocks
    private ChildDisabilityHolidayDao underTest;

    @Mock
    private ChildDisabilityHolidayRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetChildrenDisabilityHolidaysShouldReturnList() {
        //GIVEN
        List<ChildDisabilityHolidaysEntity> expected = createEntities();
        given(repository.findAll()).willReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        List<ChildDisabilityHolidaysEntity> actual = underTest.getChildrenDisabilityHolidays();
        //THEN
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<ChildDisabilityHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private ChildDisabilityHolidaysEntity createEntity() {
        return ChildDisabilityHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .childrenNumber(CHILDREN_NUMBER)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveChildDisabilityHolidayShouldSave() {
        //GIVEN
        ChildDisabilityHolidaysEntity holiday = createEntity();

        //WHEN
        verifyNoInteractions(repository);
        underTest.saveChildDisabilityHoliday(holiday);
        //THEN
        verify(repository, times(1)).save(holiday);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteChildDisabilityHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository);
        underTest.deleteChildDisabilityHoliday(HOLIDAY_ID);
        //THEN
        verify(repository, times(1)).deleteById(HOLIDAY_ID);
        verifyNoMoreInteractions(repository);
    }

}
