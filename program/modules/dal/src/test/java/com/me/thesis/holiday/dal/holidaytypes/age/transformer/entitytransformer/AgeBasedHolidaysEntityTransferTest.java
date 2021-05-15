package com.me.thesis.holiday.dal.holidaytypes.age.transformer.entitytransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.age.domain.AgeBasedHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;

/**
 * The type Age based holidays entity transfer test.
 */
class AgeBasedHolidaysEntityTransferTest {

    private static final int AGE = 1;
    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "AGE_HOLIDAY";
    private static final String DESCRIPTION = "days because of user age";
    private static final int DAYS = 20;

    @InjectMocks
    private AgeBasedHolidaysEntityTransfer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformList() {
        //GIVEN
        List<AgeBasedHolidaysEntity> entities = createEntities();
        List<AgeBasedHolidays> expected = createDomains();
        //WHEN
        List<AgeBasedHolidays> actual = underTest.transform(entities);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<AgeBasedHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<AgeBasedHolidays> createDomains() {
        return List.of(createDomain());
    }

    private AgeBasedHolidaysEntity createEntity() {
        return AgeBasedHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .age(AGE)
            .day(DAYS)
            .build();
    }

    private AgeBasedHolidays createDomain() {
        return AgeBasedHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .age(AGE)
            .day(DAYS)
            .build();
    }

    @Test
    void testTransformShouldTransformDomain() {
        //GIVEN
        AgeBasedHolidays domain = createDomain();
        AgeBasedHolidaysEntity expected = createEntity();
        //WHEN
        AgeBasedHolidaysEntity actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
