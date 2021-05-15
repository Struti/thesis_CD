package com.me.thesis.holiday.service.holidayplan;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.holidayplan.domain.Plan;
import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;
import com.me.thesis.holiday.dto.holidayplan.HolidayType;
import com.me.thesis.holiday.service.holidaytypes.AgeHolidaysService;
import com.me.thesis.holiday.service.holidaytypes.UserDisabilityHolidaysService;

/**
 * The type Person specific plan factory.
 */
@Service
public class PersonSpecificPlanFactory {

    @Autowired
    private AgeHolidaysService ageHolidaysService;

    @Autowired
    private UserDisabilityHolidaysService userDisabilityHolidaysService;

    @Autowired
    private ChildRelatedPlanFragmentProvider childRelatedPlanFragmentProvider;

    /**
     * Create specific plan.
     *
     * @param person    the person
     * @param personAge the person age
     *
     * @return the specific plan
     */
    public SpecificPlan create(PersonDetails person, int personAge) {
        SpecificPlan.SpecificPlanBuilder specificPlan = SpecificPlan.builder();
        Plan calculationPlan = getHolidayPlan(person);
        specificPlan.basicHolidays(getBasicHoliday(calculationPlan));

        if (isPartOfSpecification(calculationPlan, HolidayType.AGE)) {
            getAgeHoliday(personAge).ifPresent(specificPlan::ageBasedHolidays);
        }

        if (person.isDisability() && isPartOfSpecification(calculationPlan, HolidayType.USER_DISABILITY)) {
            getUserDisabilityHolidays().ifPresent(specificPlan::userDisabilityHolidays);
        }

        if (person.isChild() && isPartOfSpecification(calculationPlan, HolidayType.CHILD)) {
            getChildrenHolidays(person).ifPresent(specificPlan::childrenHolidays);
            getChildDisabilityHolidays(person).ifPresent(specificPlan::childDisabilityHolidays);
        }

        return specificPlan.build();
    }

    private boolean isPartOfSpecification(Plan calculationPlan, HolidayType holidayType) {
        return isCalculationPlanExist(calculationPlan) && isRelatedHoliday(calculationPlan, holidayType);
    }

    private boolean isCalculationPlanExist(Plan calculationPlan) {
        return Optional.ofNullable(calculationPlan.getFixHolidays())
            .isPresent();
    }

    private Plan getHolidayPlan(PersonDetails person) {
        return person.getCalculationPlan()
            .map(HolidayCalculationPlan::getHolidayTypes)
            .orElse(null);
    }

    private BasicHolidays getBasicHoliday(Plan plan) {
        return plan.getBasicHolidays();
    }

    private boolean isRelatedHoliday(Plan calculationPlan, HolidayType holidayType) {
        return calculationPlan.getFixHolidays()
            .stream()
            .anyMatch(type -> type.equals(holidayType));
    }

    private Optional<AgeBasedHolidays> getAgeHoliday(int personAge) {
        return ageHolidaysService.getAllAgeBasedHolidayDetails()
            .stream()
            .filter(holidays -> holidays.getAge() == personAge)
            .findFirst();
    }

    private Optional<UserDisabilityHolidays> getUserDisabilityHolidays() {
        return userDisabilityHolidaysService.getAllUserDisabilityHolidays()
            .stream()
            .findFirst();
    }

    private Optional<ChildrenHolidays> getChildrenHolidays(PersonDetails personDetails) {
        return Optional.of(childRelatedPlanFragmentProvider.getChildrenHoliday(personDetails.getChildren()
            .orElse(null)));
    }

    private Optional<ChildDisabilityHolidays> getChildDisabilityHolidays(PersonDetails personDetails) {
        return Optional.of(childRelatedPlanFragmentProvider.getChildDisabilityHoliday(personDetails.getChildren()
            .orElse(null)));
    }

}
