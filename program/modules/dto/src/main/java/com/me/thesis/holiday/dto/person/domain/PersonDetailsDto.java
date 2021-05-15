package com.me.thesis.holiday.dto.person.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.me.thesis.holiday.dto.child.ChildDetailsDto;
import com.me.thesis.holiday.dto.person.enums.PersonDtoEmployeeRole;
import com.me.thesis.holiday.dto.person.enums.PersonDtoGender;
import com.me.thesis.holiday.dto.person.enums.PersonDtoTitle;

import lombok.Builder;
import lombok.Value;

/**
 * The type Person details dto.
 */
@Value
@Builder
public class PersonDetailsDto {

    Long id;
    PersonDtoTitle title;
    String fullName;
    String birthName;
    PersonDtoGender gender;
    LocalDate birthDate;
    boolean disability;
    boolean child;
    List<ChildDetailsDto> children;
    LocalDate startDate;
    LocalDate lastDay;
    PersonDtoEmployeeRole employeeRole;
    String actualLocation;
    Long holidayCalculationPlan;
    SpecificPlanDto specificPlan;

    /**
     * Gets person id.
     *
     * @return the person id
     */
    public Optional<Long> getPersonId() {
        return Optional.ofNullable(id);
    }

    /**
     * Gets children.
     *
     * @return the children
     */
    public Optional<List<ChildDetailsDto>> getChildren() {
        return Optional.ofNullable(children);
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
     * Gets holiday calculation plan.
     *
     * @return the holiday calculation plan
     */
    public Optional<Long> getHolidayCalculationPlan() {
        return Optional.ofNullable(holidayCalculationPlan);
    }

    /**
     * Gets specific plan.
     *
     * @return the specific plan
     */
    public Optional<SpecificPlanDto> getSpecificPlan() {
        return Optional.ofNullable(specificPlan);
    }

}
