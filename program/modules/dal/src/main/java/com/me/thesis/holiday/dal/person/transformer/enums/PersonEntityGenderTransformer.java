package com.me.thesis.holiday.dal.person.transformer.enums;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;

/**
 * The type Person entity gender transformer.
 */
@Component
public class PersonEntityGenderTransformer {

    @Autowired
    private Map<PersonEntityGender, PersonDomainGender> personGenderEntityToDomainMap;

    @Autowired
    private Map<PersonDomainGender, PersonEntityGender> personGenderDomainToEntityMap;

    /**
     * Transform person entity gender.
     *
     * @param gender the gender
     *
     * @return the person entity gender
     */
    public PersonEntityGender transform(PersonDomainGender gender) {
        return personGenderDomainToEntityMap.getOrDefault(gender, PersonEntityGender.MALE);
    }

    /**
     * Transform person domain gender.
     *
     * @param gender the gender
     *
     * @return the person domain gender
     */
    public PersonDomainGender transform(PersonEntityGender gender) {
        return personGenderEntityToDomainMap.getOrDefault(gender, PersonDomainGender.MALE);
    }

}
