package com.me.thesis.holiday.service.holidaytypes;

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

import com.me.thesis.holiday.dal.holidaytypes.userdisability.dao.UserDisabilityHolidaysDao;
import com.me.thesis.holiday.dal.holidaytypes.userdisability.domain.UserDisabilityHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.userdisability.transformer.entitytransformer.UserDisabilityEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;

/**
 * The type User disability holidays service test.
 */
class UserDisabilityHolidaysServiceTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "USER_DISABILITY_HOLIDAY";
    private static final String DESCRIPTION = "days because user have some disability";
    private static final int DAY = 20;

    @InjectMocks
    private UserDisabilityHolidaysService underTest;

    @Mock
    private UserDisabilityHolidaysDao dao;

    @Mock
    private UserDisabilityEntityTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUserDisabilityHolidaysShouldReturnList() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        List<UserDisabilityHolidaysEntity> entities = createEntities();
        List<UserDisabilityHolidays> expected = createDomains();
        given(dao.getAllUserDisabilityHolidays()).willReturn(entities);
        given(transformer.transform(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(dao, transformer);
        List<UserDisabilityHolidays> actual = underTest.getAllUserDisabilityHolidays();
        //THEN
        inOrder.verify(dao, times(1))
            .getAllUserDisabilityHolidays();
        inOrder.verify(transformer, times(1))
            .transform(entities);
        verifyNoMoreInteractions(dao, transformer);
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
    void testSaveUserDisabilityHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        UserDisabilityHolidaysEntity entity = createEntity();
        UserDisabilityHolidays domain = createDomain();
        given(transformer.transform(domain)).willReturn(entity);
        //WHEN
        verifyNoInteractions(dao, transformer);
        underTest.saveUserDisabilityHoliday(domain);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(domain);
        inOrder.verify(dao, times(1))
            .saveUserDisabilityHoliday(entity);
        verifyNoMoreInteractions(dao, transformer);
    }

    @Test
    void testDeleteUserDisabilityHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(dao);
        underTest.deleteUserDisabilityHoliday(HOLIDAY_ID);
        //THEN
        verify(dao, times(1)).deleteUserDisabilityHoliday(HOLIDAY_ID);
        verifyNoMoreInteractions(dao);
    }

}
