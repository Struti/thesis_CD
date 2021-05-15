package com.me.thesis.holiday.dto.user.domain;

import com.me.thesis.holiday.dto.user.enums.UserDtoRole;

import lombok.Builder;
import lombok.Value;

/**
 * The type Create user dto.
 */
@Value
@Builder
public class CreateUserDto {

    String email;
    UserDtoRole role;
    String name;
}
