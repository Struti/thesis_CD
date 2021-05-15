package com.me.thesis.holiday.service.scheduler;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.person.PersonService;

/**
 * The type Yearly plan specification scheduler.
 */
@Service
public class YearlyPlanSpecificationScheduler {

    @Autowired
    private PersonService personService;

    /**
     * Update plans.
     *
     * @param allPerson set of all persons.
     */
    public void updatePlans(Set<PersonDetails> allPerson) {
        allPerson.stream()
            .map(PersonDetails::getId)
            .forEach(id -> personService.specifyPlan(id));
    }

}
