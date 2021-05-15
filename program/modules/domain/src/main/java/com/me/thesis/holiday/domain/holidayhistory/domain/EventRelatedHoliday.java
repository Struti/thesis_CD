package com.me.thesis.holiday.domain.holidayhistory.domain;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.me.thesis.holiday.domain.holidaytypes.EventRelatedHolidayType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "EventRelatedHolidayBuilder", access = AccessLevel.PUBLIC)
@JsonDeserialize(builder = EventRelatedHoliday.EventRelatedHolidayBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventRelatedHoliday {

    @JsonProperty("holidayType")
    EventRelatedHolidayType holidayType;
    @JsonProperty("days")
    int days;
    @JsonProperty("eventDate")
    LocalDate eventDate;
    @JsonProperty("expiration")
    LocalDate expiration;

    public Optional<LocalDate> getExpiration() {
        return Optional.ofNullable(expiration);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class EventRelatedHolidayBuilder {

    }

}
