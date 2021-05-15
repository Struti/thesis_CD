package com.me.thesis.holiday.domain.person.transformer.facade;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.PersonSelectorDomain;
import com.me.thesis.holiday.domain.person.transformer.fulltransformer.FullPersonDomainTransformer;
import com.me.thesis.holiday.domain.person.transformer.partialtransformer.PartialPersonDomainTransformer;
import com.me.thesis.holiday.dto.person.domain.PersonDetailsDto;
import com.me.thesis.holiday.dto.person.domain.PersonSelectorDto;

/**
 * The type Person domain transformer facade.
 */
@Component
public class PersonDomainTransformerFacade {

    @Autowired
    private FullPersonDomainTransformer fullPersonDomainTransformer;

    @Autowired
    private PartialPersonDomainTransformer partialPersonDomainTransformer;

    /**
     * Transform full dto to full domain person details.
     *
     * @param personDetailsDto the person details dto
     *
     * @return the person details
     */
    public PersonDetails transformFullDtoToFullDomain(PersonDetailsDto personDetailsDto) {
        return fullPersonDomainTransformer.transform(personDetailsDto);
    }

    /**
     * Transform selector domain set to full dto set set.
     *
     * @param selectorDomain the selector domain
     *
     * @return the set
     */
    public Set<PersonSelectorDto> transformSelectorDomainSetToFullDtoSet(Set<PersonSelectorDomain> selectorDomain) {
        return partialPersonDomainTransformer.transform(selectorDomain);
    }

}
