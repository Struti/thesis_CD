package com.me.thesis.holiday.dal.holidaytypes.death.transformers.entitytransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.death.domain.DeathHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.death.domain.DeathHolidays;

/**
 * The type Death holidays entity transformer test.
 */
class DeathHolidaysEntityTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "DEATH_HOLIDAY";
    private static final String DESCRIPTION = "death cause holidays";
    private static final int DAYS = 20;

    @InjectMocks
    private DeathHolidaysEntityTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformList() {
        //GIVEN
        List<DeathHolidaysEntity> entities = createEntities();
        List<DeathHolidays> expected = createDomains();
        //WHEN
        List<DeathHolidays> actual = underTest.transform(entities);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<DeathHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<DeathHolidays> createDomains() {
        return List.of(createDomain());
    }

    private DeathHolidaysEntity createEntity() {
        return DeathHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    private DeathHolidays createDomain() {
        return DeathHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    @Test
    void testTransformShouldTransformDomain() {
        //GIVEN
        DeathHolidays domain = createDomain();
        DeathHolidaysEntity expected = createEntity();
        //WHEN
        DeathHolidaysEntity actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
