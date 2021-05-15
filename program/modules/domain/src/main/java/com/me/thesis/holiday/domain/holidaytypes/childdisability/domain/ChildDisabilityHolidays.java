package com.me.thesis.holiday.domain.holidaytypes.childdisability.domain;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * The type Child disability holidays.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "ChildDisabilityHolidaysBuilder")
@JsonDeserialize(builder = ChildDisabilityHolidays.ChildDisabilityHolidaysBuilder.class)
public class ChildDisabilityHolidays {

    @JsonProperty("id")
    Long id;
    @JsonProperty("type")
    String type;
    @JsonProperty("description")
    String description;
    @JsonProperty("childrenNumber")
    int childrenNumber;
    @JsonProperty("day")
    int day;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

    /**
     * The type Child disability holidays builder.
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class ChildDisabilityHolidaysBuilder {

    }

}
