package com.me.thesis.holiday.service.person;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.person.dao.PersonDao;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.transformer.facade.PersonEntityTransformerFacade;
import com.me.thesis.holiday.dal.user.dao.UserDao;
import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.PersonSelectorDomain;
import com.me.thesis.holiday.service.holidayplan.HolidayCalculationPlanService;
import com.me.thesis.holiday.service.holidayplan.PersonSpecificPlanFactory;
import com.me.thesis.holiday.service.lib.CurrentDateProvider;

/**
 * The type Person service.
 */
@Service
public class PersonService {

    @Autowired
    private PersonEntityTransformerFacade transformer;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CurrentDateProvider currentDateProvider;

    @Autowired
    private PersonSpecificPlanFactory specificPlanFactory;

    @Autowired
    private HolidayCalculationPlanService calculationPlanService;

    /**
     * Gets person selectors.
     *
     * @return the person selectors
     */
    public Set<PersonSelectorDomain> getPersonSelectors() {
        return transformer.transformDetailsEntitiesToPartialSelectorDomain(userDao.findAllUser());
    }

    /**
     * Gets person by id.
     *
     * @param personId the person id
     *
     * @return the person by id
     */
    public PersonDetails getPersonById(Long personId) {
        return transformer.transformFullEntityDetailsToDomain(personDao.findById(personId));
    }

    /**
     * Save person.
     *
     * @param personDetails     the person details
     * @param calculationPlanId the calculation plan id
     */
    public void savePerson(PersonDetails personDetails, Long calculationPlanId) {
        HolidayCalculationPlan calculationPlan = calculationPlanService.getPlanById(calculationPlanId);
        personDao.savePerson(transformer.transformFullDomainDetailsToEntity(personDetails, calculationPlan));
    }

    /**
     * Gets all person details.
     *
     * @return the all person details
     */
    public Set<PersonDetails> getAllPersonDetails() {
        return transformer.transformFullEntityDetailsListToDomainSet(personDao.findAllPersonDetails());
    }

    /**
     * Delete person.
     *
     * @param personId the person id
     */
    public void deletePerson(Long personId) {
        personDao.deletePerson(personId);
    }

    /**
     * Specify plan.
     *
     * @param personId the person id
     */
    public void specifyPlan(Long personId) {
        PersonDetails person = findPersonById(personId);
        PersonEntity personEntity = transformer.transformFullDomainWithSpecificPlanToEntity(person, specificPlanFactory.create(person, getPersonAge(person)));
        personDao.savePerson(personEntity);
    }

    /**
     * Find person by id person details.
     *
     * @param personId the person id
     *
     * @return the person details
     */
    public PersonDetails findPersonById(Long personId) {
        return transformer.transformFullEntityDetailsToDomain(personDao.findById(personId));
    }

    /**
     * Gets person age.
     *
     * @param personDetails the person details
     *
     * @return the person age
     */
    public int getPersonAge(PersonDetails personDetails) {
        return Math.subtractExact(
            currentDateProvider.provide()
                .getYear(),
            personDetails.getBirthDate()
                .getYear()
        );
    }

}
