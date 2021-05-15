package com.me.thesis.holiday.dto.holidayplan;

import java.util.Optional;

import lombok.Builder;
import lombok.Value;

/**
 * The type Holiday calculation plan dto.
 */
@Value
@Builder
public class HolidayCalculationPlanDto {

    Long calculationPlanId;
    PlanDto holidayTypes;
    String description;

    /**
     * Gets calculation plan id.
     *
     * @return the calculation plan id
     */
    public Optional<Long> getCalculationPlanId() {
        return Optional.ofNullable(calculationPlanId);
    }

}
