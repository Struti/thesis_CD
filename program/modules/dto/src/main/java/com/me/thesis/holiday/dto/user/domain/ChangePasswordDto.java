package com.me.thesis.holiday.dto.user.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ChangePasswordDto {

    String email;
    String oldPassword;
    String newPassword;

}
