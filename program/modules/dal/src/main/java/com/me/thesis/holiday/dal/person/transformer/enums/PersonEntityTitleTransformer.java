package com.me.thesis.holiday.dal.person.transformer.enums;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.person.enums.PersonEntityTitle;
import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;

/**
 * The type Person entity title transformer.
 */
@Component
public class PersonEntityTitleTransformer {

    @Autowired
    private Map<PersonEntityTitle, PersonDomainTitle> personTitleEntityToDomainMap;

    @Autowired
    private Map<PersonDomainTitle, PersonEntityTitle> personTitleDomainToEntityMap;

    /**
     * Transform person entity title.
     *
     * @param title the title
     *
     * @return the person entity title
     */
    public PersonEntityTitle transform(PersonDomainTitle title) {
        return personTitleDomainToEntityMap.getOrDefault(title, PersonEntityTitle.NONE);
    }

    /**
     * Transform person domain title.
     *
     * @param title the title
     *
     * @return the person domain title
     */
    public PersonDomainTitle transform(PersonEntityTitle title) {
        return personTitleEntityToDomainMap.getOrDefault(title, PersonDomainTitle.NONE);
    }

}
