package com.me.thesis.holiday.dal.holidaytypes.userdisability.transformer.entitytransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.userdisability.domain.UserDisabilityHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;

/**
 * The type User disability entity transformer test.
 */
class UserDisabilityEntityTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "USER_DISABILITY_HOLIDAY";
    private static final String DESCRIPTION = "days because user have some disability";
    private static final int DAY = 20;

    @InjectMocks
    private UserDisabilityEntityTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformList() {
        //GIVEN
        List<UserDisabilityHolidaysEntity> entities = createEntities();
        List<UserDisabilityHolidays> expected = createDomains();
        //WHEN
        List<UserDisabilityHolidays> actual = underTest.transform(entities);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<UserDisabilityHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<UserDisabilityHolidays> createDomains() {
        return List.of(createDomain());
    }

    private UserDisabilityHolidaysEntity createEntity() {
        return UserDisabilityHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .build();
    }

    private UserDisabilityHolidays createDomain() {
        return UserDisabilityHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .build();
    }

    @Test
    void testTransformShouldTransformDomain() {
        //GIVEN
        UserDisabilityHolidays domain = createDomain();
        UserDisabilityHolidaysEntity expected = createEntity();
        //WHEN
        UserDisabilityHolidaysEntity actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
