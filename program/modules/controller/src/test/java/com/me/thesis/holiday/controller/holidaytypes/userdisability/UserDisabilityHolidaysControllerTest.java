package com.me.thesis.holiday.controller.holidaytypes.userdisability;

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

import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.transformer.UserDisabilityHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.UserDisabilityHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.UserDisabilityHolidaysService;

/**
 * The type User disability holidays controller test.
 */
class UserDisabilityHolidaysControllerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "USER_DISABILITY_HOLIDAY";
    private static final String DESCRIPTION = "days because user have some disability";
    private static final int DAY = 20;

    @InjectMocks
    private UserDisabilityHolidaysController underTest;

    @Mock
    private UserDisabilityHolidaysService service;

    @Mock
    private UserDisabilityHolidaysTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAgeHolidayShouldReturnWithList() {
        //GIVEN
        List<UserDisabilityHolidays> expected = createDomains();
        given(service.getAllUserDisabilityHolidays()).willReturn(expected);
        //WHEN
        verifyNoInteractions(service);
        List<UserDisabilityHolidays> actual = underTest.getAllUserDisabilityHolidays();
        //THEN
        verify(service).getAllUserDisabilityHolidays();
        verifyNoMoreInteractions(service);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<UserDisabilityHolidays> createDomains() {
        return List.of(createDomain());
    }

    private UserDisabilityHolidays createDomain() {
        return UserDisabilityHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .build();
    }

    private UserDisabilityHolidayDetailsDto createDto() {
        return UserDisabilityHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .build();
    }

    @Test
    void testSaveUserDisabilityHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(service, transformer);
        UserDisabilityHolidayDetailsDto dto = createDto();
        UserDisabilityHolidays domain = createDomain();
        given(transformer.transform(dto)).willReturn(domain);
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.saveUserDisabilityHoliday(dto);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(dto);
        inOrder.verify(service, times(1))
            .saveUserDisabilityHoliday(domain);
        verifyNoMoreInteractions(service, transformer);
    }

    @Test
    void testDeleteUserDisabilityHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service);
        underTest.deleteUserDisabilityHoliday(HOLIDAY_ID);
        //THEN
        verify(service, times(1)).deleteUserDisabilityHoliday(HOLIDAY_ID);
        verifyNoMoreInteractions(service);
    }

}
