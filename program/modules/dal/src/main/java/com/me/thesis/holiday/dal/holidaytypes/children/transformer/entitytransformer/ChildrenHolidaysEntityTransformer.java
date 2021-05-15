package com.me.thesis.holiday.dal.holidaytypes.children.transformer.entitytransformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.children.domain.ChildrenHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;

/**
 * The type Children holidays entity transformer.
 */
@Component
public class ChildrenHolidaysEntityTransformer {

    /**
     * Transform list.
     *
     * @param entities the entities
     *
     * @return the list
     */
    public List<ChildrenHolidays> transform(List<ChildrenHolidaysEntity> entities) {
        return entities.stream()
            .map(this::transform)
            .collect(Collectors.toList());
    }

    private ChildrenHolidays transform(ChildrenHolidaysEntity entity) {
        return ChildrenHolidays.builder()
            .id(entity.getId())
            .type(entity.getType())
            .description(entity.getDescription())
            .children(entity.getChildren())
            .day(entity.getDay())
            .build();
    }

    /**
     * Transform children holidays entity.
     *
     * @param domain the domain
     *
     * @return the children holidays entity
     */
    public ChildrenHolidaysEntity transform(ChildrenHolidays domain) {
        return ChildrenHolidaysEntity.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .children(domain.getChildren())
            .day(domain.getDay())
            .build();
    }

}
