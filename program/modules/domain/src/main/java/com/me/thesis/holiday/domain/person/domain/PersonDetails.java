package com.me.thesis.holiday.domain.person.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;

import lombok.Builder;
import lombok.Value;

/**
 * The type Person details.
 */
@Value
@Builder
public class PersonDetails {

    Long id;
    PersonDomainTitle title;
    String fullName;
    String birthName;
    PersonDomainGender gender;
    LocalDate birthDate;
    boolean disability;
    boolean child;
    List<ChildDetails> children;
    LocalDate startDate;
    LocalDate lastDay;
    PersonDomainEmployeeRole employeeRole;
    String actualLocation;
    HolidayHistory holidayHistory;
    HolidayCalculationPlan calculationPlan;
    SpecificPlan specificPlan;

    /**
     * Gets person id.
     *
     * @return the person id
     */
    public Optional<Long> getPersonId() {
        return Optional.ofNullable(id);
    }

    /**
     * Gets last day.
     *
     * @return the last day
     */
    public Optional<LocalDate> getLastDay() {
        return Optional.ofNullable(lastDay);
    }

    /**
     * Gets children.
     *
     * @return the children
     */
    public Optional<List<ChildDetails>> getChildren() {
        return Optional.ofNullable(children);
    }

    /**
     * Gets calculation plan.
     *
     * @return the calculation plan
     */
    public Optional<HolidayCalculationPlan> getCalculationPlan() {
        return Optional.ofNullable(calculationPlan);
    }

    /**
     * Gets specific plan.
     *
     * @return the specific plan
     */
    public Optional<SpecificPlan> getSpecificPlan() {
        return Optional.ofNullable(specificPlan);
    }

}
