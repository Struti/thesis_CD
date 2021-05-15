package com.me.thesis.holiday.dto.child;

import java.time.LocalDate;
import java.util.Optional;

import lombok.Builder;
import lombok.Value;

/**
 * The type Child details dto.
 */
@Value
@Builder
public class ChildDetailsDto {

    Long childId;
    Long person;
    boolean born;
    LocalDate expectedDate;
    LocalDate birthDate;
    boolean disability;
    LocalDate disabilityCertExpirationDate;

    /**
     * Gets child id.
     *
     * @return the child id
     */
    public Optional<Long> getChildId() {
        return Optional.ofNullable(childId);
    }

    /**
     * Gets expected date.
     *
     * @return the expected date
     */
    public Optional<LocalDate> getExpectedDate() {
        return Optional.ofNullable(expectedDate);
    }

    /**
     * Gets birth date.
     *
     * @return the birth date
     */
    public Optional<LocalDate> getBirthDate() {
        return Optional.ofNullable(birthDate);
    }

    /**
     * Gets disability cert expiration date.
     *
     * @return the disability cert expiration date
     */
    public Optional<LocalDate> getDisabilityCertExpirationDate() {
        return Optional.ofNullable(disabilityCertExpirationDate);
    }

}
