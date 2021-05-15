package com.me.thesis.holiday.dal.holidayplan.transfromer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;

import com.google.common.io.Resources;
import com.me.thesis.holiday.domain.holidayplan.domain.Plan;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.dto.holidayplan.HolidayType;

/**
 * The type Plan transformer test.
 */
class PlanTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String BASIC_TYPE = "BASIC";
    private static final String DESCRIPTION = "description";
    private static final int DAY = 10;
    private static final HolidayType AGE_HOLIDAY_TYPE = HolidayType.AGE;
    private static final HolidayType CHILD_HOLIDAY_TYPE = HolidayType.CHILD;
    private static final HolidayType CHILD_DISABILITY_HOLIDAY_TYPE = HolidayType.CHILD_DISABILITY;
    private static final HolidayType USER_DISABILITY_HOLIDAY_TYPE = HolidayType.USER_DISABILITY;
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    @InjectMocks
    private PlanTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformPlanToStringWithEmptyFixTypes() throws IOException, JSONException {
        //GIVEN
        Plan plan = createPlanWithoutFixTypes();
        URL resource = this.getClass()
            .getResource("/plan/expected/plan_without_fix_holidays.json");
        String expected = Resources.toString(resource, CHARSET);
        //WHEN
        String actual = underTest.transform(plan);
        //THEN
        System.out.println(actual);
        JSONAssert.assertEquals(expected, actual, true);
    }

    private Plan createPlanWithoutFixTypes() {
        return Plan.builder()
            .basicHolidays(createBasicHoliday())
            .build();
    }

    @Test
    void testTransformShouldTransformPlanToStringWithFixTypes() throws IOException, JSONException {
        //GIVEN
        Plan plan = createPlanWithFixTypes();
        URL resource = this.getClass()
            .getResource("/plan/expected/plan_with_fix_holidays.json");
        String expected = Resources.toString(resource, StandardCharsets.UTF_8);
        //WHEN
        String actual = underTest.transform(plan);
        //THEN
        JSONAssert.assertEquals(expected, actual, true);
    }

    private BasicHolidays createBasicHoliday() {
        return BasicHolidays.builder()
            .id(HOLIDAY_ID)
            .type(BASIC_TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .build();
    }

    private Plan createPlanWithFixTypes() {
        return Plan.builder()
            .basicHolidays(createBasicHoliday())
            .fixHolidays(createHolidayTypes())
            .build();
    }

    private List<HolidayType> createHolidayTypes() {
        return List.of(AGE_HOLIDAY_TYPE, CHILD_HOLIDAY_TYPE, CHILD_DISABILITY_HOLIDAY_TYPE, USER_DISABILITY_HOLIDAY_TYPE);
    }

    @Test
    void testTransformShouldTransformStringToPlanWithoutFixTypes() throws IOException {
        //GIVEN
        URL resource = this.getClass()
            .getResource("/plan/mockdata/plan_without_fix_holidays.json");
        String holidayPlan = Resources.toString(resource, StandardCharsets.UTF_8);
        Plan expected = createPlanWithoutFixTypes();
        //WHEN
        Plan actual = underTest.transform(holidayPlan);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void testTransformShouldTransformStringToPlanWithFixTypes() throws IOException {
        //GIVEN
        URL resource = this.getClass()
            .getResource("/plan/mockdata/plan_with_fix_holidays.json");
        String holidayPlan = Resources.toString(resource, StandardCharsets.UTF_8);
        Plan expected = createPlanWithFixTypes();
        //WHEN
        Plan actual = underTest.transform(holidayPlan);
        //THEN
        assertEquals(expected, actual);
    }

}
