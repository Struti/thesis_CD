package com.me.thesis.holiday.dal.person.transformer.facade;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.transformer.fulltransformer.FullPersonEntityTransformer;
import com.me.thesis.holiday.dal.person.transformer.partialtransformer.PartialPersonEntityTransformer;
import com.me.thesis.holiday.dal.user.domain.UserEntity;
import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.PersonSelectorDomain;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;

/**
 * The type Person entity transformer facade.
 */
@Component
public class PersonEntityTransformerFacade {

    @Autowired
    private PartialPersonEntityTransformer partialPersonEntityTransformer;

    @Autowired
    private FullPersonEntityTransformer fullPersonEntityTransformer;

    /**
     * Transform full entity details to domain person details.
     *
     * @param entity the entity
     *
     * @return the person details
     */
    public PersonDetails transformFullEntityDetailsToDomain(PersonEntity entity) {
        return fullPersonEntityTransformer.transform(entity);
    }

    /**
     * Transform full domain details to entity person entity.
     *
     * @param personDetails   the person details
     * @param calculationPlan the calculation plan
     *
     * @return the person entity
     */
    public PersonEntity transformFullDomainDetailsToEntity(PersonDetails personDetails, HolidayCalculationPlan calculationPlan) {
        return fullPersonEntityTransformer.transform(personDetails, calculationPlan);
    }

    /**
     * Transform full domain details to entity person entity.
     *
     * @param personDetails the person details
     *
     * @return the person entity
     */
    public PersonEntity transformFullDomainDetailsToEntity(PersonDetails personDetails) {
        return fullPersonEntityTransformer.transform(personDetails);
    }

    /**
     * Transform full entity details list to domain set set.
     *
     * @param personEntities the person entities
     *
     * @return the set
     */
    public Set<PersonDetails> transformFullEntityDetailsListToDomainSet(List<PersonEntity> personEntities) {
        return fullPersonEntityTransformer.transform(personEntities);
    }

    /**
     * Transform details entities to partial selector domain set.
     *
     * @param personEntities the person entities
     *
     * @return the set
     */
    public Set<PersonSelectorDomain> transformDetailsEntitiesToPartialSelectorDomain(List<UserEntity> personEntities) {
        return partialPersonEntityTransformer.transform(personEntities);
    }

    /**
     * Transform full domain with specific plan to entity person entity.
     *
     * @param person       the person
     * @param specificPlan the specific plan
     *
     * @return the person entity
     */
    public PersonEntity transformFullDomainWithSpecificPlanToEntity(PersonDetails person, SpecificPlan specificPlan) {
        return fullPersonEntityTransformer.transform(person, specificPlan);
    }

}
