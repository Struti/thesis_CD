package com.me.thesis.holiday.dal.holidaytypes.basic.transformers.entitytransformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.basic.domain.BasicHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;

/**
 * The type Basic holidays entity transformer.
 */
@Component
public class BasicHolidaysEntityTransformer {

    /**
     * Transform list.
     *
     * @param entities the entities
     *
     * @return the list
     */
    public List<BasicHolidays> transform(List<BasicHolidaysEntity> entities) {
        return entities.stream()
            .map(this::transform)
            .collect(Collectors.toList());
    }

    private BasicHolidays transform(BasicHolidaysEntity entity) {
        return BasicHolidays.builder()
            .id(entity.getId())
            .type(entity.getType())
            .description(entity.getDescription())
            .day(entity.getDay())
            .build();
    }

    /**
     * Transform basic holidays entity.
     *
     * @param domain the domain
     *
     * @return the basic holidays entity
     */
    public BasicHolidaysEntity transform(BasicHolidays domain) {
        return BasicHolidaysEntity.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .day(domain.getDay())
            .build();
    }

}
