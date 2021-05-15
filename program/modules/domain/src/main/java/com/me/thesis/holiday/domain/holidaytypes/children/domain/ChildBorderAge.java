package com.me.thesis.holiday.domain.holidaytypes.children.domain;

import java.util.Optional;

import lombok.Builder;
import lombok.Value;

/**
 * The type Child border age.
 */
@Value
@Builder
public class ChildBorderAge {

    Long id;
    int childBorderAge;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

}
