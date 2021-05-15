package com.me.thesis.holiday.dal.holidaytypes.dad.transformers.entitytransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.dad.domain.DadHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.dad.domain.DadHolidays;

/**
 * The type Dad holidays entity transformer test.
 */
class DadHolidaysEntityTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "DAD_HOLIDAY";
    private static final String DESCRIPTION = "days because of birth, month limit";
    private static final int LIMIT = 200;
    private static final int DAYS = 20;

    @InjectMocks
    private DadHolidaysEntityTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformList() {
        //GIVEN
        List<DadHolidaysEntity> entities = createEntities();
        List<DadHolidays> expected = createDomains();
        //WHEN
        List<DadHolidays> actual = underTest.transform(entities);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<DadHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<DadHolidays> createDomains() {
        return List.of(createDomain());
    }

    private DadHolidaysEntity createEntity() {
        return DadHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .limit(LIMIT)
            .build();
    }

    private DadHolidays createDomain() {
        return DadHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .limit(LIMIT)
            .build();
    }

    @Test
    void testTransformShouldTransformDomain() {
        //GIVEN
        DadHolidays domain = createDomain();
        DadHolidaysEntity expected = createEntity();
        //WHEN
        DadHolidaysEntity actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
