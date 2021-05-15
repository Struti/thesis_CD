package com.me.thesis.holiday.domain.holidayplan.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.dto.holidayplan.HolidayType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * The type Plan.
 */
@Value
@Builder(builderClassName = "PlanBuilder", access = AccessLevel.PUBLIC)
@JsonDeserialize(builder = Plan.PlanBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Plan {

    @JsonProperty("basicHolidays")
    BasicHolidays basicHolidays;
    @JsonProperty("fixHolidays")
    List<HolidayType> fixHolidays;

    /**
     * The type Plan builder.
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class PlanBuilder {

    }

}
