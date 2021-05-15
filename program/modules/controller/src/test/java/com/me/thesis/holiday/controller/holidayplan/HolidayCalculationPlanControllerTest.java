package com.me.thesis.holiday.controller.holidayplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.holidayplan.domain.Plan;
import com.me.thesis.holiday.domain.holidayplan.transformer.HolidayCalculationPlanTransformer;
import com.me.thesis.holiday.dto.holidayplan.HolidayCalculationPlanDto;
import com.me.thesis.holiday.service.holidayplan.HolidayCalculationPlanService;

/**
 * The type Holiday calculation plan controller test.
 */
class HolidayCalculationPlanControllerTest {

    private static final long CALCULATION_PLAN_ID = 1111111L;
    private static final String DESCRIPTION = "desc";

    @InjectMocks
    private HolidayCalculationPlanController underTest;

    @Mock
    private HolidayCalculationPlanService service;

    @Mock
    private HolidayCalculationPlanTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHolidayCalculationPlanShouldReturnWithList() {
        //GIVEN
        List<HolidayCalculationPlan> expected = createDomains();
        given(service.getHolidayCalculationPlan()).willReturn(expected);
        //WHEN
        verifyNoInteractions(service);
        List<HolidayCalculationPlan> actual = underTest.getHolidayCalculationPlan();
        //THEN
        verify(service).getHolidayCalculationPlan();
        verifyNoMoreInteractions(service);
        assertEquals(expected, actual);
    }

    private List<HolidayCalculationPlan> createDomains() {
        return List.of(createDomain());
    }

    @Test
    void testSaveHolidayCalculationPlanShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(service, transformer);
        HolidayCalculationPlanDto dto = createDto();
        HolidayCalculationPlan domain = createDomain();
        given(transformer.transform(dto)).willReturn(domain);
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.saveHolidayCalculationPlan(dto);
        //THEN
        inOrder.verify(transformer)
            .transform(dto);
        inOrder.verify(service)
            .saveHolidayCalculationPlan(domain);
        verifyNoMoreInteractions(service, transformer);
    }

    private HolidayCalculationPlanDto createDto() {
        return HolidayCalculationPlanDto.builder()
            .build();
    }

    private HolidayCalculationPlan createDomain() {
        return HolidayCalculationPlan.builder()
            .calculationPlanId(CALCULATION_PLAN_ID)
            .holidayTypes(createHolidayTypes())
            .description(DESCRIPTION)
            .build();
    }

    private Plan createHolidayTypes() {
        return Plan.builder()
            .build();
    }

    @Test
    void testDeleteHolidayCalculationPlanShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service);
        underTest.deleteHolidayCalculationPlan(CALCULATION_PLAN_ID);
        //THEN
        verify(service).deleteHolidayCalculationPlan(CALCULATION_PLAN_ID);
        verifyNoMoreInteractions(service);
    }

}
