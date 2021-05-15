package com.me.thesis.holiday.dal.holidaytypes.basic.transformers.entitytransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.basic.domain.BasicHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;

/**
 * The type Basic holidays entity transformer test.
 */
class BasicHolidaysEntityTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "BASIC_HOLIDAY";
    private static final String DESCRIPTION = "basic holidays";
    private static final int DAYS = 20;

    @InjectMocks
    private BasicHolidaysEntityTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformList() {
        //GIVEN
        List<BasicHolidaysEntity> entities = createEntities();
        List<BasicHolidays> expected = createDomains();
        //WHEN
        List<BasicHolidays> actual = underTest.transform(entities);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<BasicHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<BasicHolidays> createDomains() {
        return List.of(createDomain());
    }

    private BasicHolidaysEntity createEntity() {
        return BasicHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    private BasicHolidays createDomain() {
        return BasicHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    @Test
    void testTransformShouldTransformDomain() {
        //GIVEN
        BasicHolidays domain = createDomain();
        BasicHolidaysEntity expected = createEntity();
        //WHEN
        BasicHolidaysEntity actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
