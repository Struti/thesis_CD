package com.me.thesis.holiday.domain.holidayplan.factory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidayplan.domain.Plan;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.domain.holidaytypes.basic.transformer.BasicHolidaysTransformer;
import com.me.thesis.holiday.dto.holidayplan.HolidayType;
import com.me.thesis.holiday.dto.holidayplan.PlanDto;
import com.me.thesis.holiday.dto.holidaytypes.BasicHolidayDetailsDto;

/**
 * The type Plan factory.
 */
@Component
public class PlanFactory {

    @Autowired
    private BasicHolidaysTransformer basicHolidaysTransformer;

    /**
     * Create plan.
     *
     * @param planDto the plan dto
     *
     * @return the plan
     */
    public Plan create(PlanDto planDto) {
        Plan.PlanBuilder plan = Plan.builder();
        plan.basicHolidays(getBasicHolidays(planDto.getBasicHolidays()));
        getFixHolidays(planDto).ifPresent(holidayType -> plan.fixHolidays(planDto.getFixHolidays()));
        return plan.build();
    }

    private BasicHolidays getBasicHolidays(BasicHolidayDetailsDto basicHolidays) {
        return basicHolidaysTransformer.transform(basicHolidays);
    }

    private Optional<List<HolidayType>> getFixHolidays(PlanDto planDto) {
        return Optional.ofNullable(planDto.getFixHolidays());
    }

}
