package com.me.thesis.holiday.domain.holidayplan.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.holidayplan.factory.PlanFactory;
import com.me.thesis.holiday.dto.holidayplan.HolidayCalculationPlanDto;

/**
 * The type Holiday calculation plan transformer.
 */
@Component
public class HolidayCalculationPlanTransformer {

    @Autowired
    private PlanFactory planFactory;

    /**
     * Transform holiday calculation plan.
     *
     * @param planDto the plan dto
     *
     * @return the holiday calculation plan
     */
    public HolidayCalculationPlan transform(HolidayCalculationPlanDto planDto) {
        return HolidayCalculationPlan.builder()
            .calculationPlanId(planDto.getCalculationPlanId()
                .orElse(null))
            .holidayTypes(planFactory.create(planDto.getHolidayTypes()))
            .description(planDto.getDescription())
            .build();
    }

}
