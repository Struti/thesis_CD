package com.me.thesis.holiday.domain.person.transformer.enums;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.dto.person.enums.PersonDtoGender;

/**
 * The type Person domain gender transformer.
 */
@Component
public class PersonDomainGenderTransformer {

    @Autowired
    private Map<PersonDtoGender, PersonDomainGender> personGenderMap;

    /**
     * Transform person domain gender.
     *
     * @param gender the gender
     *
     * @return the person domain gender
     */
    public PersonDomainGender transform(PersonDtoGender gender) {
        return personGenderMap.get(gender);
    }

}
