package com.me.thesis.holiday.domain.holidayplan.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.holidayplan.domain.Plan;
import com.me.thesis.holiday.domain.holidayplan.factory.PlanFactory;
import com.me.thesis.holiday.dto.holidayplan.HolidayCalculationPlanDto;
import com.me.thesis.holiday.dto.holidayplan.PlanDto;

/**
 * The type Holiday calculation plan transformer test.
 */
class HolidayCalculationPlanTransformerTest {

    private static final long CALCULATION_PLAN_ID = 1111111L;
    private static final PlanDto HOLIDAY_TYPES_DTO = PlanDto.builder()
        .build();
    private static final String DESCRIPTION = "desc";
    private static final Plan HOLIDAY_TYPES_DOMAIN = Plan.builder()
        .build();

    @InjectMocks
    private HolidayCalculationPlanTransformer underTest;

    @Mock
    private PlanFactory planFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private HolidayCalculationPlan createDomain() {
        return HolidayCalculationPlan.builder()
            .calculationPlanId(CALCULATION_PLAN_ID)
            .description(DESCRIPTION)
            .holidayTypes(HOLIDAY_TYPES_DOMAIN)
            .build();
    }

    private HolidayCalculationPlanDto createDto() {
        return HolidayCalculationPlanDto.builder()
            .calculationPlanId(CALCULATION_PLAN_ID)
            .holidayTypes(HOLIDAY_TYPES_DTO)
            .description(DESCRIPTION)
            .build();
    }

    @Test
    void testTransformShould() {
        //GIVEN
        HolidayCalculationPlan expected = createDomain();
        HolidayCalculationPlanDto dto = createDto();
        when(planFactory.create(dto.getHolidayTypes())).thenReturn(HOLIDAY_TYPES_DOMAIN);
        //WHEN
        verifyNoInteractions(planFactory);
        HolidayCalculationPlan actual = underTest.transform(dto);
        //THEN
        verify(planFactory).create(dto.getHolidayTypes());
        verifyNoMoreInteractions(planFactory);
        assertEquals(expected, actual);
    }

}
