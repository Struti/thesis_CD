package com.me.thesis.holiday.dal.holidaytypes.childdisability.transformer.entitytransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.childdisability.domain.ChildDisabilityHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;

/**
 * The type Child disability holidays entity transformer test.
 */
class ChildDisabilityHolidaysEntityTransformerTest {

    private static final int CHILDREN_NUMBER = 1;
    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "CHILD_DISABILITY_HOLIDAY";
    private static final String DESCRIPTION = "days because of 1 child disability";
    private static final int DAYS = 20;

    @InjectMocks
    private ChildDisabilityHolidaysEntityTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformList() {
        //GIVEN
        List<ChildDisabilityHolidaysEntity> entities = createEntities();
        List<ChildDisabilityHolidays> expected = createDomains();
        //WHEN
        List<ChildDisabilityHolidays> actual = underTest.transform(entities);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<ChildDisabilityHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<ChildDisabilityHolidays> createDomains() {
        return List.of(createDomain());
    }

    private ChildDisabilityHolidaysEntity createEntity() {
        return ChildDisabilityHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .childrenNumber(CHILDREN_NUMBER)
            .day(DAYS)
            .build();
    }

    private ChildDisabilityHolidays createDomain() {
        return ChildDisabilityHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .childrenNumber(CHILDREN_NUMBER)
            .day(DAYS)
            .build();
    }

    @Test
    void testTransformShouldTransformDomain() {
        //GIVEN
        ChildDisabilityHolidays domain = createDomain();
        ChildDisabilityHolidaysEntity expected = createEntity();
        //WHEN
        ChildDisabilityHolidaysEntity actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
