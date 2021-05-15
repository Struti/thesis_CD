package com.me.thesis.holiday.dto.person.domain;

import lombok.Builder;
import lombok.Value;

/**
 * The type Person selector dto.
 */
@Value
@Builder
public class PersonSelectorDto {

    Long id;
    String name;
    String email;
}
