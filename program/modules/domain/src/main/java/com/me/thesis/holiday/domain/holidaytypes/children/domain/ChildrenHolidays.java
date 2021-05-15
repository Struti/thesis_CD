package com.me.thesis.holiday.domain.holidaytypes.children.domain;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * The type Children holidays.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "ChildrenHolidaysBuilder")
@JsonDeserialize(builder = ChildrenHolidays.ChildrenHolidaysBuilder.class)
public class ChildrenHolidays {

    @JsonProperty("id")
    Long id;
    @JsonProperty("type")
    String type;
    @JsonProperty("description")
    String description;
    @JsonProperty("children")
    int children;
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
     * The type Children holidays builder.
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class ChildrenHolidaysBuilder {

    }

}
