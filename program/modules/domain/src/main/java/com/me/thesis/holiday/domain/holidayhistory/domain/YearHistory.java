package com.me.thesis.holiday.domain.holidayhistory.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * The type Year history.
 */
@Value
@Builder(builderClassName = "YearHistoryBuilder", access = AccessLevel.PUBLIC)
@JsonDeserialize(builder = YearHistory.YearHistoryBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class YearHistory {

    @JsonProperty("year")
    int year;
    @JsonProperty("fixHolidaysForTheYear")
    BigDecimal fixHolidaysForTheYear;
    @JsonProperty("eventRelatedHolidays")
    List<EventRelatedHoliday> eventRelatedHolidays;
    @JsonProperty("holidayEvent")
    List<HolidayEvent> holidayEvents;

    /**
     * The type Year history builder.
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class YearHistoryBuilder {

    }

}
