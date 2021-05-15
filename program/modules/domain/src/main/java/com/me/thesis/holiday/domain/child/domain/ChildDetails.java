package com.me.thesis.holiday.domain.child.domain;

import java.time.LocalDate;
import java.util.Optional;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;

import lombok.Builder;
import lombok.Value;

/**
 * The type Child details.
 */
@Value
@Builder
public class ChildDetails {

    Long childId;
    PersonDetails person;
    boolean born;
    LocalDate expectedDate;
    LocalDate birthDate;
    boolean disability;
    LocalDate disabilityCertExpirationDate;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Optional<Long> getId() {
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
