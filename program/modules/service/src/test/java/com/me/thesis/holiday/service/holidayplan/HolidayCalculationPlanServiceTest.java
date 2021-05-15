package com.me.thesis.holiday.service.holidayplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidayplan.dao.HolidayCalculationPlanDao;
import com.me.thesis.holiday.dal.holidayplan.domain.HolidayCalculationPlanEntity;
import com.me.thesis.holiday.dal.holidayplan.transfromer.HolidayCalculationPlanEntityTransformer;
import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.holidayplan.domain.Plan;

/**
 * The type Holiday calculation plan service test.
 */
class HolidayCalculationPlanServiceTest {

    private static final long CALCULATION_PLAN_ID = 111111L;
    private static final String DESCRIPTION = "description";
    private static final String HOLIDAY_TYPES = "";

    @InjectMocks
    private HolidayCalculationPlanService underTest;

    @Mock
    private HolidayCalculationPlanDao dao;

    @Mock
    private HolidayCalculationPlanEntityTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHolidayCalculationPlanShouldReturnList() {
        //GIVEN
        List<HolidayCalculationPlanEntity> entities = createEntities();
        List<HolidayCalculationPlan> expected = createDomains();
        given(dao.getHolidayCalculationPlan()).willReturn(entities);
        given(transformer.transform(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(dao, transformer);
        List<HolidayCalculationPlan> actual = underTest.getHolidayCalculationPlan();
        //THEN
        verify(dao).getHolidayCalculationPlan();
        verify(transformer).transform(entities);
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
    }

    private List<HolidayCalculationPlanEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<HolidayCalculationPlan> createDomains() {
        return List.of(createDomain());
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

    private Plan createPlan() {
        return Plan.builder()
            .build();
    }

    @Test
    void testSaveHolidayCalculationPlanShouldSaveAndReturnString() {
        //GIVEN
        HolidayCalculationPlanEntity entity = createEntity();
        HolidayCalculationPlan domain = createDomain();
        given(transformer.transform(domain)).willReturn(entity);
        //WHEN
        verifyNoInteractions(dao, transformer);
        underTest.saveHolidayCalculationPlan(domain);
        //THEN
        verify(transformer).transform(domain);
        verify(dao).saveHolidayCalculationPlan(entity);
        verifyNoMoreInteractions(dao, transformer);
    }

    @Test
    void testDeleteHolidayCalculationPlanShouldDeleteAndReturnString() {
        //GIVEN
        //WHEN
        verifyNoInteractions(dao);
        underTest.deleteHolidayCalculationPlan(CALCULATION_PLAN_ID);
        //THEN
        verify(dao).deleteHolidayCalculationPlan(CALCULATION_PLAN_ID);
        verifyNoMoreInteractions(dao);
    }

    @Test
    void testGetPlanByIdShouldReturnWithPlan() {
        //GIVEN
        HolidayCalculationPlanEntity entity = createEntity();
        HolidayCalculationPlan expected = createDomain();
        given(dao.getPlanById(CALCULATION_PLAN_ID)).willReturn(entity);
        given(transformer.transform(entity)).willReturn(expected);
        //WHEN
        verifyNoInteractions(dao, transformer);
        HolidayCalculationPlan actual = underTest.getPlanById(CALCULATION_PLAN_ID);
        //THEN
        verify(dao).getPlanById(CALCULATION_PLAN_ID);
        verify(transformer).transform(entity);
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
    }

}
