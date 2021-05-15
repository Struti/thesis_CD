package com.me.thesis.holiday.domain.person.domain;

import lombok.Builder;
import lombok.Value;

/**
 * The type Person selector domain.
 */
@Value
@Builder
public class PersonSelectorDomain {

    Long id;
    String name;
    String email;
}
