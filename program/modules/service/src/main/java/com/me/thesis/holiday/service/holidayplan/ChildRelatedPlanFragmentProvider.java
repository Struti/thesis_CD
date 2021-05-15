package com.me.thesis.holiday.service.holidayplan;

import static java.util.function.Predicate.not;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.service.child.ChildService;
import com.me.thesis.holiday.service.holidaytypes.ChildBorderAgeService;
import com.me.thesis.holiday.service.holidaytypes.ChildDisabilityHolidaysService;
import com.me.thesis.holiday.service.holidaytypes.ChildrenHolidaysService;

/**
 * The type Child related plan fragment provider.
 */
@Component
public class ChildRelatedPlanFragmentProvider {

    @Autowired
    private ChildrenHolidaysService childrenHolidaysService;

    @Autowired
    private ChildBorderAgeService childBorderAgeService;

    @Autowired
    private ChildService childService;

    @Autowired
    private ChildDisabilityHolidaysService childDisabilityHolidaysService;

    /**
     * Gets children holiday.
     *
     * @param childrenDetails the children details
     *
     * @return the children holiday
     */
    public ChildrenHolidays getChildrenHoliday(List<ChildDetails> childrenDetails) {
        Long childrenCountUnderBorderAge = getPersonChildrenCountUnderBorderAge(childrenDetails);
        return childrenHolidaysService.getAllChildrenHolidayDetails()
            .stream()
            .filter(holidays -> holidays.getChildren() == childrenCountUnderBorderAge)
            .findFirst()
            .orElse(null);
    }

    private Long getPersonChildrenCountUnderBorderAge(List<ChildDetails> childrenDetails) {
        Integer borderAge = childBorderAgeService.getAge();
        return childrenDetails.stream()
            .filter(ChildDetails::isBorn)
            .filter(childDetails -> childService.getChildAge(getChildAge(childDetails)) <= borderAge)
            .count();
    }

    private LocalDate getChildAge(ChildDetails childDetails) {
        return childDetails.getBirthDate()
            .orElse(null);
    }

    /**
     * Gets child disability holiday.
     *
     * @param childrenDetails the children details
     *
     * @return the child disability holiday
     */
    public ChildDisabilityHolidays getChildDisabilityHoliday(List<ChildDetails> childrenDetails) {
        return childDisabilityHolidaysService.getAllChildDisabilityHolidayDetails()
            .stream()
            .filter(holidays -> holidays.getChildrenNumber() == getPersonChildrenDisabilityCount(childrenDetails))
            .findFirst()
            .orElse(null);
    }

    private Long getPersonChildrenDisabilityCount(List<ChildDetails> childrenDetails) {
        return childrenDetails.stream()
            .filter(not(ChildDetails::isDisability))
            .count();
    }

}
