package com.me.thesis.holiday.dal.holidaytypes.children.transformer.entitytransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.children.domain.ChildrenHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;

/**
 * The type Children holidays entity transformer test.
 */
class ChildrenHolidaysEntityTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "CHILD_HOLIDAY";
    private static final String DESCRIPTION = "days because children";
    private static final int DAYS = 20;
    private static final int CHILDREN = 3;

    @InjectMocks
    private ChildrenHolidaysEntityTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformList() {
        //GIVEN
        List<ChildrenHolidaysEntity> entities = createEntities();
        List<ChildrenHolidays> expected = createDomains();
        //WHEN
        List<ChildrenHolidays> actual = underTest.transform(entities);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<ChildrenHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<ChildrenHolidays> createDomains() {
        return List.of(createDomain());
    }

    private ChildrenHolidaysEntity createEntity() {
        return ChildrenHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .children(CHILDREN)
            .day(DAYS)
            .build();
    }

    private ChildrenHolidays createDomain() {
        return ChildrenHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .children(CHILDREN)
            .day(DAYS)
            .build();
    }

    @Test
    void testTransformShouldTransformDomain() {
        //GIVEN
        ChildrenHolidays domain = createDomain();
        ChildrenHolidaysEntity expected = createEntity();
        //WHEN
        ChildrenHolidaysEntity actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
