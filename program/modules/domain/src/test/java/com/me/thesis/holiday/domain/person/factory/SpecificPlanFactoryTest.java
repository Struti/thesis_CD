package com.me.thesis.holiday.domain.person.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;
import com.me.thesis.holiday.domain.holidaytypes.age.transformer.AgeHolidaysTransformer;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.domain.holidaytypes.basic.transformer.BasicHolidaysTransformer;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.transformer.ChildDisabilityHolidaysTransformer;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.domain.holidaytypes.children.transformer.ChildrenHolidaysTransformer;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.transformer.UserDisabilityHolidaysTransformer;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;
import com.me.thesis.holiday.dto.holidaytypes.AgeHolidayDetailsDto;
import com.me.thesis.holiday.dto.holidaytypes.BasicHolidayDetailsDto;
import com.me.thesis.holiday.dto.holidaytypes.ChildDisabilityHolidayDetailsDto;
import com.me.thesis.holiday.dto.holidaytypes.ChildrenHolidayDetailsDto;
import com.me.thesis.holiday.dto.holidaytypes.UserDisabilityHolidayDetailsDto;
import com.me.thesis.holiday.dto.person.domain.SpecificPlanDto;

/**
 * The type Specific plan factory test.
 */
class SpecificPlanFactoryTest {

    private static final long ID = 11111L;
    private static final String TYPE = "type";
    private static final String DESCRIPTION = "desc";
    private static final int DAY = 20;
    private static final int AGE = 25;
    private static final int CHILDREN = 2;
    @InjectMocks
    private SpecificPlanFactory underTest;

    @Mock
    private BasicHolidaysTransformer basicHolidaysTransformer;
    @Mock
    private AgeHolidaysTransformer ageHolidaysTransformer;
    @Mock
    private UserDisabilityHolidaysTransformer userDisabilityHolidaysTransformer;
    @Mock
    private ChildrenHolidaysTransformer childrenHolidaysTransformer;
    @Mock
    private ChildDisabilityHolidaysTransformer childDisabilityHolidaysTransformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformWithBasicOnly() {
        //GIVEN
        SpecificPlan expected = createBasicOnlyDomain();
        SpecificPlanDto dto = createBasicOnlyDto();
        when(basicHolidaysTransformer.transform(dto.getBasicHolidays())).thenReturn(expected.getBasicHolidays());
        //WHEN
        verifyNoInteractions(basicHolidaysTransformer, ageHolidaysTransformer, userDisabilityHolidaysTransformer, childDisabilityHolidaysTransformer, childrenHolidaysTransformer);
        SpecificPlan actual = underTest.transform(dto);
        //THEN
        verify(basicHolidaysTransformer).transform(dto.getBasicHolidays());
        verifyNoMoreInteractions(basicHolidaysTransformer, ageHolidaysTransformer, userDisabilityHolidaysTransformer, childDisabilityHolidaysTransformer,
            childrenHolidaysTransformer);
        assertEquals(expected, actual);
    }

    private SpecificPlan createBasicOnlyDomain() {
        return SpecificPlan.builder()
            .basicHolidays(createBasicHolidaysDomain())
            .build();
    }

    private SpecificPlanDto createBasicOnlyDto() {
        return SpecificPlanDto.builder()
            .basicHolidays(createBasicHolidaysDto())
            .build();
    }

    private BasicHolidays createBasicHolidaysDomain() {
        return BasicHolidays.builder()
            .id(ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .build();
    }

    private BasicHolidayDetailsDto createBasicHolidaysDto() {
        return BasicHolidayDetailsDto.builder()
            .id(ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .build();
    }

    @Test
    void testTransformShouldTransformWithFixHolidays() {
        //GIVEN
        SpecificPlan expected = createFullDomain();
        SpecificPlanDto dto = createFullDto();
        when(basicHolidaysTransformer.transform(dto.getBasicHolidays())).thenReturn(expected.getBasicHolidays());
        when(ageHolidaysTransformer.transform(dto.getAgeBasedHolidays()
            .orElse(null))).thenReturn(expected.getAgeBasedHolidays()
            .orElse(null));
        when(userDisabilityHolidaysTransformer.transform(dto.getUserDisabilityHolidays()
            .orElse(null))).thenReturn(expected.getUserDisabilityHolidays()
            .orElse(null));
        when(childDisabilityHolidaysTransformer.transform(dto.getChildDisabilityHolidays()
            .orElse(null))).thenReturn(expected.getChildDisabilityHolidays()
            .orElse(null));
        when(childrenHolidaysTransformer.transform(dto.getChildrenHolidays()
            .orElse(null))).thenReturn(expected.getChildrenHolidays()
            .orElse(null));
        //WHEN
        verifyNoInteractions(basicHolidaysTransformer, ageHolidaysTransformer, userDisabilityHolidaysTransformer, childDisabilityHolidaysTransformer, childrenHolidaysTransformer);
        SpecificPlan actual = underTest.transform(dto);
        //THEN
        verify(basicHolidaysTransformer).transform(dto.getBasicHolidays());
        verify(ageHolidaysTransformer).transform(dto.getAgeBasedHolidays()
            .orElse(null));
        verify(userDisabilityHolidaysTransformer).transform(dto.getUserDisabilityHolidays()
            .orElse(null));
        verify(childrenHolidaysTransformer).transform(dto.getChildrenHolidays()
            .orElse(null));
        verify(childDisabilityHolidaysTransformer).transform(dto.getChildDisabilityHolidays()
            .orElse(null));
        verifyNoMoreInteractions(basicHolidaysTransformer, ageHolidaysTransformer, userDisabilityHolidaysTransformer, childDisabilityHolidaysTransformer,
            childrenHolidaysTransformer);
        assertEquals(expected, actual);
    }

    private SpecificPlan createFullDomain() {
        return SpecificPlan.builder()
            .basicHolidays(createBasicHolidaysDomain())
            .ageBasedHolidays(createAgeBasedHolidaysDomain())
            .userDisabilityHolidays(createUserDisabilityHolidaysDomain())
            .childrenHolidays(createChildrenHolidaysDomain())
            .childDisabilityHolidays(createChildDisabilityHolidaysDomain())
            .build();
    }

    private SpecificPlanDto createFullDto() {
        return SpecificPlanDto.builder()
            .basicHolidays(createBasicHolidaysDto())
            .ageBasedHolidays(createAgeDto())
            .userDisabilityHolidays(createUserDisabilityHolidaysDto())
            .childrenHolidays(createChildrenHolidayDetailsDto())
            .childDisabilityHolidays(createChildDisabilityHolidayDetailsDto())
            .build();
    }

    private AgeBasedHolidays createAgeBasedHolidaysDomain() {
        return AgeBasedHolidays.builder()
            .id(ID)
            .type(TYPE)
            .age(AGE)
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

    private UserDisabilityHolidays createUserDisabilityHolidaysDomain() {
        return UserDisabilityHolidays.builder()
            .id(ID)
            .type(TYPE)
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

    private ChildrenHolidays createChildrenHolidaysDomain() {
        return ChildrenHolidays.builder()
            .id(ID)
            .type(TYPE)
            .children(CHILDREN)
            .description(DESCRIPTION)
            .build();
    }

    private ChildDisabilityHolidays createChildDisabilityHolidaysDomain() {
        return ChildDisabilityHolidays.builder()
            .id(ID)
            .type(TYPE)
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

    private AgeHolidayDetailsDto createAgeDto() {
        return AgeHolidayDetailsDto.builder()
            .id(ID)
            .type(TYPE)
            .age(AGE)
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

    private UserDisabilityHolidayDetailsDto createUserDisabilityHolidaysDto() {
        return UserDisabilityHolidayDetailsDto.builder()
            .id(ID)
            .type(TYPE)
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

    private ChildrenHolidayDetailsDto createChildrenHolidayDetailsDto() {
        return ChildrenHolidayDetailsDto.builder()
            .id(ID)
            .type(TYPE)
            .children(CHILDREN)
            .description(DESCRIPTION)
            .build();
    }

    private ChildDisabilityHolidayDetailsDto createChildDisabilityHolidayDetailsDto() {
        return ChildDisabilityHolidayDetailsDto.builder()
            .id(ID)
            .type(TYPE)
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

}
