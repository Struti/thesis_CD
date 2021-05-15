package com.me.thesis.holiday.dal.child.transformer.fulltransformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.child.domain.ChildEntity;
import com.me.thesis.holiday.dal.person.transformer.fulltransformer.FullPersonEntityTransformer;
import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;

/**
 * The type Child full entity transformer.
 */
@Component
public class ChildFullEntityTransformer {

    @Autowired
    private FullPersonEntityTransformer personTransformer;

    /**
     * Transform list.
     *
     * @param children the children
     *
     * @return the list
     */
    public List<ChildDetails> transform(List<ChildEntity> children) {
        return children.stream()
            .map(this::transform)
            .collect(Collectors.toList());
    }

    /**
     * Transform child details.
     *
     * @param child the child
     *
     * @return the child details
     */
    public ChildDetails transform(ChildEntity child) {
        return ChildDetails.builder()
            .childId(child.getChildId())
            .person(personTransformer.transform(child.getPerson()))
            .born(child.isBorn())
            .expectedDate(child.getExpectedDate())
            .birthDate(child.getBirthDate())
            .disability(child.isDisability())
            .disabilityCertExpirationDate(child.getDisabilityCertExpirationDate())
            .build();
    }

    /**
     * Transform child entity.
     *
     * @param child  the child
     * @param person the person
     *
     * @return the child entity
     */
    public ChildEntity transform(ChildDetails child, PersonDetails person) {
        ChildEntity entity = transform(child);
        entity.setPerson(personTransformer.transform(person));
        return entity;
    }

    private ChildEntity transform(ChildDetails child) {
        return ChildEntity.builder()
            .childId(child.getChildId())
            .born(child.isBorn())
            .expectedDate(child.getExpectedDate()
                .orElse(null))
            .birthDate(child.getBirthDate()
                .orElse(null))
            .disability(child.isDisability())
            .disabilityCertExpirationDate(child.getDisabilityCertExpirationDate()
                .orElse(null))
            .build();
    }

}
