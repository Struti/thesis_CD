package com.me.thesis.holiday.domain.holidaytypes.userdisability.domain;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * The type User disability holidays.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "UserDisabilityHolidaysBuilder")
@JsonDeserialize(builder = UserDisabilityHolidays.UserDisabilityHolidaysBuilder.class)
public class UserDisabilityHolidays {

    @JsonProperty("id")
    Long id;
    @JsonProperty("type")
    String type;
    @JsonProperty("description")
    String description;
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
     * The type User disability holidays builder.
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class UserDisabilityHolidaysBuilder {

    }

}
