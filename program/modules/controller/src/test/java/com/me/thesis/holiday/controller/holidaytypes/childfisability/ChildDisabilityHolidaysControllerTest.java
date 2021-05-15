package com.me.thesis.holiday.controller.holidaytypes.childfisability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.controller.holidaytypes.childdisability.ChildDisabilityHolidaysController;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.transformer.ChildDisabilityHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.ChildDisabilityHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.ChildDisabilityHolidaysService;

/**
 * The type Child disability holidays controller test.
 */
class ChildDisabilityHolidaysControllerTest {

    private static final int CHILDREN_NUMBER = 1;
    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "CHILD_DISABILITY_HOLIDAY";
    private static final String DESCRIPTION = "days because of 1 child disability";
    private static final int DAYS = 20;

    @InjectMocks
    private ChildDisabilityHolidaysController underTest;

    @Mock
    private ChildDisabilityHolidaysService service;

    @Mock
    private ChildDisabilityHolidaysTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAgeHolidayShouldReturnWithList() {
        //GIVEN
        List<ChildDisabilityHolidays> expected = createDomains();
        given(service.getAllChildDisabilityHolidayDetails()).willReturn(expected);
        //WHEN
        verifyNoInteractions(service);
        List<ChildDisabilityHolidays> actual = underTest.getAllChildDisabilityHolidayDetails();
        //THEN
        verify(service).getAllChildDisabilityHolidayDetails();
        verifyNoMoreInteractions(service);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<ChildDisabilityHolidays> createDomains() {
        return List.of(createDomain());
    }

    private ChildDisabilityHolidays createDomain() {
        return ChildDisabilityHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .childrenNumber(CHILDREN_NUMBER)
            .day(DAYS)
            .build();
    }

    private ChildDisabilityHolidayDetailsDto createDto() {
        return ChildDisabilityHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .childrenNumber(CHILDREN_NUMBER)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveChildDisabilityHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(service, transformer);
        ChildDisabilityHolidayDetailsDto dto = createDto();
        ChildDisabilityHolidays domain = createDomain();
        given(transformer.transform(dto)).willReturn(domain);
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.saveChildDisabilityHoliday(dto);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(dto);
        inOrder.verify(service, times(1))
            .saveChildDisabilityHoliday(domain);
        verifyNoMoreInteractions(service, transformer);
    }

    @Test
    void testDeleteChildDisabilityHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service);
        underTest.deleteChildDisabilityHoliday(HOLIDAY_ID);
        //THEN
        verify(service, times(1)).deleteChildDisabilityHolidayDetails(HOLIDAY_ID);
        verifyNoMoreInteractions(service);
    }

}
