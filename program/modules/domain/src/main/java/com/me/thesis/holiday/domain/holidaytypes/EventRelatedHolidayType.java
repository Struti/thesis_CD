package com.me.thesis.holiday.domain.holidaytypes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EventRelatedHolidayType {
    DAD("dad"),
    DEATH("death"),
    MATERNITY("maternity"),
    DAD_PENALTY("unused_dad_holidays");

    private final String type;

    EventRelatedHolidayType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}
