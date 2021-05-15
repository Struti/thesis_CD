package com.me.thesis.holiday.domain.holidaytypes.maternity.domain;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * The type Maternity holiday.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "MaternityHolidayBuilder")
@JsonDeserialize(builder = MaternityHoliday.MaternityHolidayBuilder.class)
public class MaternityHoliday {

    @JsonProperty("id")
    Long id;
    @JsonProperty("type")
    String type;
    @JsonProperty("description")
    String description;
    @JsonProperty("beforeDays")
    int beforeDays;
    @JsonProperty("afterDays")
    int afterDays;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

    /**
     * The type Maternity holiday builder.
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class MaternityHolidayBuilder {

    }

}
