package com.me.thesis.holiday.domain.person.transformer.partialtransformer;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.person.domain.PersonSelectorDomain;
import com.me.thesis.holiday.dto.person.domain.PersonSelectorDto;

/**
 * The type Partial person domain transformer.
 */
@Component
public class PartialPersonDomainTransformer {

    /**
     * Transform set.
     *
     * @param selectorDomains the selector domains
     *
     * @return the set
     */
    public Set<PersonSelectorDto> transform(Set<PersonSelectorDomain> selectorDomains) {
        return selectorDomains.stream()
            .map(this::transform)
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private PersonSelectorDto transform(PersonSelectorDomain domain) {
        return PersonSelectorDto.builder()
            .id(domain.getId())
            .email(domain.getEmail())
            .name(domain.getName())
            .build();
    }

}
