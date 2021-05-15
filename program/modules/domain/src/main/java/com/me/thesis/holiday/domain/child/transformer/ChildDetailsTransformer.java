package com.me.thesis.holiday.domain.child.transformer;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.dto.child.ChildDetailsDto;

/**
 * The type Child details transformer.
 */
@Component
public class ChildDetailsTransformer {

    /**
     * Transform child details.
     *
     * @param child the child
     *
     * @return the child details
     */
    public ChildDetails transform(ChildDetailsDto child) {
        return ChildDetails.builder()
            .childId(child.getChildId()
                .orElse(null))
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
