package com.me.thesis.holiday.controller.holidaytypes.children;

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

import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.domain.holidaytypes.children.transformer.ChildrenHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.ChildrenHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.ChildrenHolidaysService;

/**
 * The type Children holidays controller test.
 */
class ChildrenHolidaysControllerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "CHILD_HOLIDAY";
    private static final String DESCRIPTION = "days because children";
    private static final int DAYS = 20;
    private static final int CHILDREN = 3;

    @InjectMocks
    private ChildrenHolidaysController underTest;

    @Mock
    private ChildrenHolidaysService service;

    @Mock
    private ChildrenHolidaysTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAgeHolidayShouldReturnWithList() {
        //GIVEN
        List<ChildrenHolidays> expected = createDomains();
        given(service.getAllChildrenHolidayDetails()).willReturn(expected);
        //WHEN
        verifyNoInteractions(service, transformer);
        List<ChildrenHolidays> actual = underTest.getAllChildrenHolidayDetails();
        //THEN
        verify(service).getAllChildrenHolidayDetails();
        verifyNoMoreInteractions(service, transformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<ChildrenHolidays> createDomains() {
        return List.of(createDomain());
    }

    private ChildrenHolidays createDomain() {
        return ChildrenHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .children(CHILDREN)
            .day(DAYS)
            .build();
    }

    private ChildrenHolidayDetailsDto createDto() {
        return ChildrenHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .children(CHILDREN)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveChildrenHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(service, transformer);
        ChildrenHolidayDetailsDto dto = createDto();
        ChildrenHolidays domain = createDomain();
        given(transformer.transform(dto)).willReturn(domain);
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.saveChildrenHoliday(dto);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(dto);
        inOrder.verify(service, times(1))
            .saveChildrenHoliday(domain);
        verifyNoMoreInteractions(service, transformer);
    }

    @Test
    void testDeleteChildrenHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service);
        underTest.deleteChildrenHoliday(HOLIDAY_ID);
        //THEN
        verify(service, times(1)).deleteAgeBasedHolidayDetails(HOLIDAY_ID);
        verifyNoMoreInteractions(service);
    }

}
