package com.me.thesis.holiday.domain.holidaytypes.age.domain;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * The type Age based holidays.
 */
@Value
@Builder(builderClassName = "AgeBasedHolidaysBuilder")
@JsonDeserialize(builder = AgeBasedHolidays.AgeBasedHolidaysBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AgeBasedHolidays {

    @JsonProperty("id")
    Long id;
    @JsonProperty("type")
    String type;
    @JsonProperty("description")
    String description;
    @JsonProperty("age")
    int age;
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
     * The type Age based holidays builder.
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class AgeBasedHolidaysBuilder {

    }

}
