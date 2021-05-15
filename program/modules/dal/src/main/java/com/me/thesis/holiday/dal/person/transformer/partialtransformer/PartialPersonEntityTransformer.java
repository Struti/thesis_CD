package com.me.thesis.holiday.dal.person.transformer.partialtransformer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.user.domain.UserEntity;
import com.me.thesis.holiday.domain.person.domain.PersonSelectorDomain;

/**
 * The type Partial person entity transformer.
 */
@Component
public class PartialPersonEntityTransformer {

    /**
     * Transform set.
     *
     * @param personEntities the person entities
     *
     * @return the set
     */
    public Set<PersonSelectorDomain> transform(List<UserEntity> personEntities) {
        return personEntities.stream()
            .map(this::transform)
            .collect(Collectors.toSet());
    }

    private PersonSelectorDomain transform(UserEntity entity) {
        return PersonSelectorDomain.builder()
            .id(entity.getPersonEntity()
                .getPersonId())
            .name(entity.getPersonEntity()
                .getFullName())
            .email(entity.getEmail())
            .build();
    }

}
