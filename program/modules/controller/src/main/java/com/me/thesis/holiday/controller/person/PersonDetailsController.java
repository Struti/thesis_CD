package com.me.thesis.holiday.controller.person;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.transformer.facade.PersonDomainTransformerFacade;
import com.me.thesis.holiday.dto.person.domain.PersonDetailsDto;
import com.me.thesis.holiday.dto.person.domain.PersonSelectorDto;
import com.me.thesis.holiday.service.person.PersonService;

/**
 * The type Person details controller.
 */
@RestController
public class PersonDetailsController {

    private static final String ADMIN_PERSON_SELECTOR = "/admin/person/selector";
    private static final String ADMIN_PERSON_GET_DETAILS = "/admin/person/details";
    private static final String ADMIN_PERSON_SAVE_DETAILS = "/admin/person/save";
    private static final String ADMIN_PERSON_ALL_DETAILS = "/admin/person/allPersonDetails";
    private static final String ADMIN_PERSON_DELETE = "/admin/person/delete/{id}";
    private static final String ADMIN_PERSON_SPECIFY_HOLIDAY_PLAN = "/admin/person/specifyHolidayPlan";

    @Autowired
    private PersonService service;

    @Autowired
    private PersonDomainTransformerFacade transformer;

    /**
     * Gets person selectors.
     *
     * @return the person selectors
     */
    @GetMapping(ADMIN_PERSON_SELECTOR)
    public Set<PersonSelectorDto> getPersonSelectors() {
        return transformer.transformSelectorDomainSetToFullDtoSet(service.getPersonSelectors());
    }

    /**
     * Gets person details.
     *
     * @param personId the person id
     *
     * @return the person details
     */
    @GetMapping(ADMIN_PERSON_GET_DETAILS)
    public PersonDetails getPersonDetails(Long personId) {
        return service.getPersonById(personId);
    }

    /**
     * Save person.
     *
     * @param personDetailsDto the person details dto
     */
    @PostMapping(ADMIN_PERSON_SAVE_DETAILS)
    public void savePerson(@RequestBody PersonDetailsDto personDetailsDto) {
        service.savePerson(transformer.transformFullDtoToFullDomain(personDetailsDto), personDetailsDto.getHolidayCalculationPlan()
            .orElse(null));
    }

    /**
     * Gets all person details.
     *
     * @return the all person details
     */
    @GetMapping(ADMIN_PERSON_ALL_DETAILS)
    public Set<PersonDetails> getAllPersonDetails() {
        return service.getAllPersonDetails();
    }

    /**
     * Delete person.
     *
     * @param personId the person id
     */
    @DeleteMapping(ADMIN_PERSON_DELETE)
    public void deletePerson(@PathVariable("id") Long personId) {
        service.deletePerson(personId);
    }

    /**
     * Specify holiday plan.
     *
     * @param personId the person id
     */
    @PostMapping(ADMIN_PERSON_SPECIFY_HOLIDAY_PLAN)
    public void specifyHolidayPlan(Long personId) {
        service.specifyPlan(personId);
    }

}
