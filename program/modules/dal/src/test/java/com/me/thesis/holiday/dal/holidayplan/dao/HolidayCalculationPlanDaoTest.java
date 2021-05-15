package com.me.thesis.holiday.dal.holidayplan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidayplan.domain.HolidayCalculationPlanEntity;
import com.me.thesis.holiday.dal.holidayplan.repository.HolidayCalculationPlanRepository;

/**
 * The type Holiday calculation plan dao test.
 */
class HolidayCalculationPlanDaoTest {

    private static final long CALCULATION_PLAN_ID = 111111L;
    private static final String HOLIDAY_TYPES = "{}";
    private static final String DESCRIPTION = "description";

    @InjectMocks
    private HolidayCalculationPlanDao underTest;

    @Mock
    private HolidayCalculationPlanRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHolidayCalculationPlanShouldReturnListOfEntities() {
        //GIVEN
        List<HolidayCalculationPlanEntity> expected = createEntities();
        when(repository.findAll()).thenReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        List<HolidayCalculationPlanEntity> actual = underTest.getHolidayCalculationPlan();
        //THEN
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<HolidayCalculationPlanEntity> createEntities() {
        return List.of(createEntity());
    }

    private HolidayCalculationPlanEntity createEntity() {
        return HolidayCalculationPlanEntity.builder()
            .calculationPlanId(CALCULATION_PLAN_ID)
            .holidayTypes(HOLIDAY_TYPES)
            .description(DESCRIPTION)
            .build();
    }

    @Test
    void testSaveHolidayCalculationPlanShouldSaveAndReturnWithDescription() {
        //GIVEN
        HolidayCalculationPlanEntity entity = createEntity();
        //WHEN
        verifyNoInteractions(repository);
        underTest.saveHolidayCalculationPlan(entity);
        //THEN
        verify(repository).save(entity);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteHolidayCalculationPlanShouldDeleteAndReturnWithDescription() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository);
        underTest.deleteHolidayCalculationPlan(CALCULATION_PLAN_ID);
        //THEN
        verify(repository).deleteById(CALCULATION_PLAN_ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testGetPlanByIdShouldReturnWithPlan() {
        //GIVEN
        HolidayCalculationPlanEntity expected = createEntity();
        when(repository.findById(CALCULATION_PLAN_ID)).thenReturn(Optional.ofNullable(expected));
        //WHEN
        verifyNoInteractions(repository);
        HolidayCalculationPlanEntity actual = underTest.getPlanById(CALCULATION_PLAN_ID);
        //THEN
        verify(repository).findById(CALCULATION_PLAN_ID);
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
    }

    @Test
    void testGetPlanByIdShouldThrowException() {
        //GIVEN
        when(repository.findById(CALCULATION_PLAN_ID)).thenThrow(NoSuchElementException.class);
        //WHEN
        verifyNoInteractions(repository);
        //THEN
        assertThrows(NoSuchElementException.class, () -> underTest.getPlanById(CALCULATION_PLAN_ID));
        verify(repository).findById(CALCULATION_PLAN_ID);
        verifyNoMoreInteractions(repository);
    }

}
