package com.me.thesis.holiday.domain.person.domain;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * The type Specific plan.
 */
@Value
@Builder(builderClassName = "SpecificPlanBuilder", access = AccessLevel.PUBLIC)
@JsonDeserialize(builder = SpecificPlan.SpecificPlanBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecificPlan {

    @JsonProperty("basicHolidays")
    BasicHolidays basicHolidays;
    @JsonProperty("ageBasedHolidays")
    AgeBasedHolidays ageBasedHolidays;
    @JsonProperty("userDisabilityHolidays")
    UserDisabilityHolidays userDisabilityHolidays;
    @JsonProperty("childrenHolidays")
    ChildrenHolidays childrenHolidays;
    @JsonProperty("childDisabilityHolidays")
    ChildDisabilityHolidays childDisabilityHolidays;

    /**
     * Gets age based holidays.
     *
     * @return the age based holidays
     */
    public Optional<AgeBasedHolidays> getAgeBasedHolidays() {
        return Optional.ofNullable(ageBasedHolidays);
    }

    /**
     * Gets user disability holidays.
     *
     * @return the user disability holidays
     */
    public Optional<UserDisabilityHolidays> getUserDisabilityHolidays() {
        return Optional.ofNullable(userDisabilityHolidays);
    }

    /**
     * Gets children holidays.
     *
     * @return the children holidays
     */
    public Optional<ChildrenHolidays> getChildrenHolidays() {
        return Optional.ofNullable(childrenHolidays);
    }

    /**
     * Gets child disability holidays.
     *
     * @return the child disability holidays
     */
    public Optional<ChildDisabilityHolidays> getChildDisabilityHolidays() {
        return Optional.ofNullable(childDisabilityHolidays);
    }

    /**
     * The type Specific plan builder.
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class SpecificPlanBuilder {

    }

}
