package com.me.thesis.holiday.service.holidayhistory;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.service.lib.CurrentDateProvider;
import com.me.thesis.holiday.service.person.PersonService;

@Service
public class ChildBornHolidaysService {

    @Autowired
    private PersonService personService;

    @Autowired
    private DadHolidaysHistoryService dadHolidaysHistoryService;

    @Autowired
    private MaternityHolidayHistoryService maternityHolidayHistoryService;

    @Autowired
    private CurrentDateProvider currentDateProvider;

    public void validate() {
        Set<PersonDetails> allPersonDetails = getPersonDetails();
        Set<PersonDetails> personsWithChild = getPersonsWithChild(allPersonDetails);
        List<ChildDetails> momsWithExpectedChild = getMomsWithExpectedChild(personsWithChild);
        List<ChildDetails> dadsWithChild = getDadsWithChild(personsWithChild);
        LocalDate currentDate = currentDateProvider.provide();
        maternityHolidayHistoryService.addHolidays(momsWithExpectedChild, currentDate);
        dadHolidaysHistoryService.addHolidays(dadsWithChild, currentDate);
    }

    private Set<PersonDetails> getPersonDetails() {
        return personService.getAllPersonDetails();
    }

    private Set<PersonDetails> getPersonsWithChild(Set<PersonDetails> allPersonDetails) {
        return allPersonDetails.stream()
            .filter(PersonDetails::isChild)
            .collect(Collectors.toSet());
    }

    private List<ChildDetails> getMomsWithExpectedChild(Set<PersonDetails> personsWithChild) {
        return personsWithChild.stream()
            .filter(person -> getGender(person).equals(PersonDomainGender.FEMALE))
            .map(PersonDetails::getChildren)
            .flatMap(Optional::stream)
            .flatMap(Collection::stream)
            .filter(Predicate.not(ChildDetails::isBorn))
            .collect(Collectors.toList());
    }

    private List<ChildDetails> getDadsWithChild(Set<PersonDetails> personsWithChild) {
        return personsWithChild.stream()
            .filter(person -> getGender(person).equals(PersonDomainGender.MALE))
            .map(PersonDetails::getChildren)
            .flatMap(Optional::stream)
            .flatMap(Collection::stream)
            .filter(ChildDetails::isBorn)
            .collect(Collectors.toList());
    }

    private PersonDomainGender getGender(PersonDetails person) {
        return person.getGender();
    }

}
