package com.me.thesis.holiday.domain.holidayhistory.domain;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * The type Holiday event.
 */
@Value
@Builder(builderClassName = "HolidayEventBuilder", access = AccessLevel.PUBLIC)
@JsonDeserialize(builder = HolidayEvent.HolidayEventBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HolidayEvent {

    @JsonProperty("description")
    String description;
    @JsonProperty("startDate")
    LocalDate startDate;
    @JsonProperty("endDate")
    LocalDate endDate;
    @JsonProperty("dadHoliday")
    Boolean dadHoliday;

    public Optional<Boolean> isDadHoliday() {
        return Optional.ofNullable(dadHoliday);
    }

    /**
     * The type Holiday event builder.
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class HolidayEventBuilder {

    }

}
