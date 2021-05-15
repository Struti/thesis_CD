package com.me.thesis.holiday.domain.holidayhistory.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;

import lombok.Builder;
import lombok.Value;

/**
 * The type Holiday history.
 */
@Value
@Builder
public class HolidayHistory {

    Long id;
    PersonDetails person;
    @JsonProperty("yearHistory")
    List<YearHistory> yearHistories;
}
