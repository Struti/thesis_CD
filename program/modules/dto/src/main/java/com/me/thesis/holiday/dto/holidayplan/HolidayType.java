package com.me.thesis.holiday.dto.holidayplan;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Holiday type.
 */
public enum HolidayType {

    AGE("age"),
    USER_DISABILITY("userDisability"),
    CHILD("child"),
    CHILD_DISABILITY("childDisability");

    private String value;

    HolidayType(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @JsonValue
    public String getValue() {
        return value;
    }
}
