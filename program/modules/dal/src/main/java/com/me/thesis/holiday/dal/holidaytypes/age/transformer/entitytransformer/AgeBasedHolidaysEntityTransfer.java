package com.me.thesis.holiday.dal.holidaytypes.age.transformer.entitytransformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.age.domain.AgeBasedHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;

/**
 * The type Age based holidays entity transfer.
 */
@Component
public class AgeBasedHolidaysEntityTransfer {

    /**
     * Transform list.
     *
     * @param entities the entities
     *
     * @return the list
     */
    public List<AgeBasedHolidays> transform(List<AgeBasedHolidaysEntity> entities) {
        return entities.stream()
            .map(this::transform)
            .collect(Collectors.toList());
    }

    private AgeBasedHolidays transform(AgeBasedHolidaysEntity entity) {
        return AgeBasedHolidays.builder()
            .id(entity.getId())
            .type(entity.getType())
            .description(entity.getDescription())
            .age(entity.getAge())
            .day(entity.getDay())
            .build();
    }

    /**
     * Transform age based holidays entity.
     *
     * @param domain the domain
     *
     * @return the age based holidays entity
     */
    public AgeBasedHolidaysEntity transform(AgeBasedHolidays domain) {
        return AgeBasedHolidaysEntity.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .age(domain.getAge())
            .day(domain.getDay())
            .build();
    }

}
