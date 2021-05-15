package com.me.thesis.holiday.dto.holidayhistory;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DeathEventDto {

    Long personId;
    LocalDate eventDate;

}
