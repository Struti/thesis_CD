package com.me.thesis.holiday.domain.user.domain;

import com.me.thesis.holiday.domain.user.enums.UserDomainRole;

import lombok.Builder;
import lombok.Value;

/**
 * The type Create user domain.
 */
@Value
@Builder
public class CreateUserDomain {

    String email;
    UserDomainRole role;
    String name;

}
