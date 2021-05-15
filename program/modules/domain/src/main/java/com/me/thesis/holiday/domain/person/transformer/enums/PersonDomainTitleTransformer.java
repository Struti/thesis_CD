package com.me.thesis.holiday.domain.person.transformer.enums;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;
import com.me.thesis.holiday.dto.person.enums.PersonDtoTitle;

/**
 * The type Person domain title transformer.
 */
@Component
public class PersonDomainTitleTransformer {

    @Autowired
    private Map<PersonDtoTitle, PersonDomainTitle> personTitleMap;

    /**
     * Transform person domain title.
     *
     * @param title the title
     *
     * @return the person domain title
     */
    public PersonDomainTitle transform(PersonDtoTitle title) {
        return personTitleMap.get(title);
    }

}
