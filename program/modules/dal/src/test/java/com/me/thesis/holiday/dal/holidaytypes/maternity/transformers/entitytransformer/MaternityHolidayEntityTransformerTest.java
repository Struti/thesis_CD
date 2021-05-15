package com.me.thesis.holiday.dal.holidaytypes.maternity.transformers.entitytransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.maternity.domain.MaternityHolidayEntity;
import com.me.thesis.holiday.domain.holidaytypes.maternity.domain.MaternityHoliday;

/**
 * The type Maternity holiday entity transformer test.
 */
class MaternityHolidayEntityTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "MOM_GIVE_BIRTH_HOLIDAY";
    private static final String DESCRIPTION = "days before expected date";
    private static final int DAYS = 20;

    @InjectMocks
    private MaternityHolidayEntityTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformList() {
        //GIVEN
        List<MaternityHolidayEntity> entities = createEntities();
        List<MaternityHoliday> expected = createDomains();
        //WHEN
        List<MaternityHoliday> actual = underTest.transform(entities);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<MaternityHolidayEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<MaternityHoliday> createDomains() {
        return List.of(createDomain());
    }

    private MaternityHolidayEntity createEntity() {
        return MaternityHolidayEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .beforeDays(DAYS)
            .afterDays(DAYS)
            .build();
    }

    private MaternityHoliday createDomain() {
        return MaternityHoliday.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .beforeDays(DAYS)
            .afterDays(DAYS)
            .build();
    }

    @Test
    void testTransformShouldTransformDomain() {
        //GIVEN
        MaternityHoliday domain = createDomain();
        MaternityHolidayEntity expected = createEntity();
        //WHEN
        MaternityHolidayEntity actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
