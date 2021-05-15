package com.me.thesis.holiday.domain.holidayplan.domain;

import java.util.Optional;

import lombok.Builder;
import lombok.Value;

/**
 * The entity type Holiday calculation plan.
 */
@Value
@Builder
public class HolidayCalculationPlan {

    Long calculationPlanId;
    Plan holidayTypes;
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
