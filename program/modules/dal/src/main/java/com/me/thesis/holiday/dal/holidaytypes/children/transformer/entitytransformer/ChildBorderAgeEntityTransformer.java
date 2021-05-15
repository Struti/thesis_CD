package com.me.thesis.holiday.dal.holidaytypes.children.transformer.entitytransformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.children.domain.ChildBorderAgeEntity;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildBorderAge;

/**
 * The type Child border age entity transformer.
 */
@Component
public class ChildBorderAgeEntityTransformer {

    /**
     * Transform child border age.
     *
     * @param childBorderAge the child border age
     *
     * @return the child border age
     */
    public ChildBorderAge transform(ChildBorderAgeEntity childBorderAge) {
        return ChildBorderAge.builder()
            .id(childBorderAge.getChildAgeBorderId())
            .childBorderAge(childBorderAge.getBorderAge())
            .build();
    }

    /**
     * Transform child border age entity.
     *
     * @param childBorderAge the child border age
     *
     * @return the child border age entity
     */
    public ChildBorderAgeEntity transform(ChildBorderAge childBorderAge) {
        return ChildBorderAgeEntity.builder()
            .childAgeBorderId(childBorderAge.getId()
                .orElse(null))
            .borderAge(childBorderAge.getChildBorderAge())
            .build();
    }

}
