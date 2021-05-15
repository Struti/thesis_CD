package com.me.thesis.holiday.dal.person.transformer.fulltransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;

import com.google.common.io.Resources;
import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;

/**
 * The type Special plan transformer test.
 */
class SpecialPlanTransformerTest {

    private static final int CHILDREN = 2;
    private static final int AGE = 30;
    private static final long ID = 1111L;
    private static final String TYPE = "type";
    private static final int DAY = 20;
    private static final String DESCRIPTION = "desc";

    @InjectMocks
    private SpecialPlanTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformPlanToStringWithNulls() throws IOException, JSONException {
        //GIVEN
        SpecificPlan plan = createPlanWithNulls();
        URL resource = this.getClass()
            .getResource("/specialplan/expected/plan_with_nulls.json");
        String expected = Resources.toString(resource, StandardCharsets.UTF_8);
        //WHEN
        String actual = underTest.transform(plan);
        //THEN
        JSONAssert.assertEquals(expected, actual, true);
    }

    private SpecificPlan createPlanWithNulls() {
        return SpecificPlan.builder()
            .basicHolidays(createBasicHolidays())
            .build();
    }

    private BasicHolidays createBasicHolidays() {
        return BasicHolidays.builder()
            .id(ID)
            .type(TYPE)
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

    @Test
    void testTestTransformShouldTransformPlanToString() throws IOException, JSONException {
        //GIVEN
        SpecificPlan plan = createPlan();
        URL resource = this.getClass()
            .getResource("/specialplan/expected/specific_plan.json");
        String expected = Resources.toString(resource, StandardCharsets.UTF_8);
        //WHEN
        String actual = underTest.transform(plan);
        //THEN
        JSONAssert.assertEquals(expected, actual, true);
    }

    private SpecificPlan createPlan() {
        return SpecificPlan.builder()
            .basicHolidays(createBasicHolidays())
            .ageBasedHolidays(createAgeBasedHolidays())
            .childDisabilityHolidays(creatChildDisabilityHolidays())
            .childrenHolidays(creatChildrenHolidays())
            .userDisabilityHolidays(createUserDisabilityHolidays())
            .build();
    }

    private AgeBasedHolidays createAgeBasedHolidays() {
        return AgeBasedHolidays.builder()
            .id(ID)
            .description(DESCRIPTION)
            .age(AGE)
            .day(DAY)
            .type(TYPE)
            .build();
    }

    private ChildDisabilityHolidays creatChildDisabilityHolidays() {
        return ChildDisabilityHolidays.builder()
            .id(ID)
            .description(DESCRIPTION)
            .day(DAY)
            .type(TYPE)
            .childrenNumber(CHILDREN)
            .build();
    }

    private ChildrenHolidays creatChildrenHolidays() {
        return ChildrenHolidays.builder()
            .id(ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .children(CHILDREN)
            .build();
    }

    private UserDisabilityHolidays createUserDisabilityHolidays() {
        return UserDisabilityHolidays.builder()
            .id(ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .build();
    }

    @Test
    void testTransformShouldTransformStringToPlanWithNulls() throws IOException {
        //GIVEN
        URL resource = this.getClass()
            .getResource("/specialplan/mockdata/plan_with_nulls.json");
        String holidayPlan = Resources.toString(resource, StandardCharsets.UTF_8);
        SpecificPlan expected = createPlanWithNulls();
        //WHEN
        SpecificPlan actual = underTest.transform(holidayPlan);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void testTransformShouldTransformStringToPlan() throws IOException {
        //GIVEN
        URL resource = this.getClass()
            .getResource("/specialplan/mockdata/specific_plan.json");
        String holidayPlan = Resources.toString(resource, StandardCharsets.UTF_8);
        SpecificPlan expected = createPlan();
        //WHEN
        SpecificPlan actual = underTest.transform(holidayPlan);
        //THEN
        assertEquals(expected, actual);
    }

}
