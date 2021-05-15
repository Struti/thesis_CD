package com.me.thesis.holiday.domain.user.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;

import lombok.Builder;
import lombok.Value;

/**
 * The type Create user helper.
 */
@Value
public class CreateUserHelper {

    CreateUserDomain user;
    String password;
    PersonDetails personDetails;
    HolidayHistory holidayHistory;

    @Builder
    private CreateUserHelper(CreateUserDomain user, String password, LocalDate currentDate) {
        this.user = user;
        this.password = password;
        this.personDetails = createPersonDetails(user);
        this.holidayHistory = createHolidayHistory(currentDate);
    }

    private PersonDetails createPersonDetails(CreateUserDomain user) {
        return PersonDetails.builder()
            .fullName(user.getName())
            .birthName(user.getName())
            .build();
    }

    private HolidayHistory createHolidayHistory(LocalDate currentDate) {
        return HolidayHistory.builder()
            .yearHistories(createYearHistory(currentDate))
            .build();
    }

    private List<YearHistory> createYearHistory(LocalDate currentDate) {
        return List.of(YearHistory.builder()
            .year(currentDate.getYear())
            .eventRelatedHolidays(Collections.emptyList())
            .holidayEvents(Collections.emptyList())
            .build());
    }

}
