package com.me.thesis.holiday.dal.holidayplan.transfromer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidayplan.domain.HolidayCalculationPlanEntity;
import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.holidayplan.domain.Plan;

/**
 * The type Holiday calculation plan entity transformer test.
 */
class HolidayCalculationPlanEntityTransformerTest {

    private static final long CALCULATION_PLAN_ID = 111111L;
    private static final String DESCRIPTION = "description";
    private static final String HOLIDAY_TYPES = "";

    @InjectMocks
    private HolidayCalculationPlanEntityTransformer underTest;

    @Mock
    private PlanTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformEntityList() {
        //GIVEN
        List<HolidayCalculationPlanEntity> entities = createEntities();
        List<HolidayCalculationPlan> expected = createDomains();
        Plan plan = createPlan();
        when(transformer.transform(HOLIDAY_TYPES)).thenReturn(plan);
        //WHEN
        verifyNoInteractions(transformer);
        List<HolidayCalculationPlan> actual = underTest.transform(entities);
        //THEN
        verify(transformer).transform(HOLIDAY_TYPES);
        verifyNoMoreInteractions(transformer);
        assertEquals(expected, actual);
    }

    private List<HolidayCalculationPlanEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<HolidayCalculationPlan> createDomains() {
        return List.of(createDomain());
    }

    private Plan createPlan() {
        return Plan.builder()
            .build();
    }

    private HolidayCalculationPlanEntity createEntity() {
        return HolidayCalculationPlanEntity.builder()
            .calculationPlanId(CALCULATION_PLAN_ID)
            .description(DESCRIPTION)
            .holidayTypes(HOLIDAY_TYPES)
            .build();
    }

    private HolidayCalculationPlan createDomain() {
        return HolidayCalculationPlan.builder()
            .calculationPlanId(CALCULATION_PLAN_ID)
            .description(DESCRIPTION)
            .holidayTypes(createPlan())
            .build();
    }

    @Test
    void testTestTransformShouldTransformDomain() {
        //GIVEN
        HolidayCalculationPlan domain = createDomain();
        HolidayCalculationPlanEntity expected = createEntity();
        Plan plan = createPlan();
        when(transformer.transform(plan)).thenReturn(HOLIDAY_TYPES);
        //WHEN
        verifyNoInteractions(transformer);
        HolidayCalculationPlanEntity actual = underTest.transform(domain);
        //THEN
        verify(transformer).transform(plan);
        verifyNoMoreInteractions(transformer);
        assertEquals(expected, actual);
    }

}
