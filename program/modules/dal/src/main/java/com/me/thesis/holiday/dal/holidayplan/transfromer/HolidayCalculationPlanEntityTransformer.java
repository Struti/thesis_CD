package com.me.thesis.holiday.dal.holidayplan.transfromer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidayplan.domain.HolidayCalculationPlanEntity;
import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;

/**
 * The type Holiday calculation plan entity transformer.
 */
@Component
public class HolidayCalculationPlanEntityTransformer {

    @Autowired
    private PlanTransformer transformer;

    /**
     * Transform list.
     *
     * @param planEntities the plan entities
     *
     * @return the list
     */
    public List<HolidayCalculationPlan> transform(List<HolidayCalculationPlanEntity> planEntities) {
        return planEntities.stream()
            .map(this::transform)
            .collect(Collectors.toList());
    }

    /**
     * Transform holiday calculation plan.
     *
     * @param plan the plan
     *
     * @return the holiday calculation plan
     */
    public HolidayCalculationPlan transform(HolidayCalculationPlanEntity plan) {
        return HolidayCalculationPlan.builder()
            .calculationPlanId(plan.getCalculationPlanId())
            .holidayTypes(transformer.transform(plan.getHolidayTypes()))
            .description(plan.getDescription())
            .build();
    }

    /**
     * Transform holiday calculation plan entity.
     *
     * @param plan the plan
     *
     * @return the holiday calculation plan entity
     */
    public HolidayCalculationPlanEntity transform(HolidayCalculationPlan plan) {
        return HolidayCalculationPlanEntity.builder()
            .calculationPlanId(plan.getCalculationPlanId()
                .orElse(null))
            .holidayTypes(transformer.transform(plan.getHolidayTypes()))
            .description(plan.getDescription())
            .build();
    }

}
