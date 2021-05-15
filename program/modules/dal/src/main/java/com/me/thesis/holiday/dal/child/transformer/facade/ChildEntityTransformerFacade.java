package com.me.thesis.holiday.dal.child.transformer.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.child.domain.ChildEntity;
import com.me.thesis.holiday.dal.child.transformer.fulltransformer.ChildFullEntityTransformer;
import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;

/**
 * The type Child entity transformer facade.
 */
@Component
public class ChildEntityTransformerFacade {

    @Autowired
    private ChildFullEntityTransformer fullTransformer;

    /**
     * Transform full entity list to domain list list.
     *
     * @param children the children
     *
     * @return the list
     */
    public List<ChildDetails> transformFullEntityListToDomainList(List<ChildEntity> children) {
        return fullTransformer.transform(children);
    }

    /**
     * Transform child domain to entity child entity.
     *
     * @param childDomain the child domain
     * @param person      the person
     *
     * @return the child entity
     */
    public ChildEntity transformChildDomainToEntity(ChildDetails childDomain, PersonDetails person) {
        return fullTransformer.transform(childDomain, person);
    }

    /**
     * Transform child entity to domain child details.
     *
     * @param modifyChild the modify child
     *
     * @return the child details
     */
    public ChildDetails transformChildEntityToDomain(ChildEntity modifyChild) {
        return fullTransformer.transform(modifyChild);
    }

}
