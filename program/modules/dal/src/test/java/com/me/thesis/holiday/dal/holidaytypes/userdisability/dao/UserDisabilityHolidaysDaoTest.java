package com.me.thesis.holiday.dal.holidaytypes.userdisability.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.userdisability.domain.UserDisabilityHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.userdisability.repository.UserDisabilityHolidayRepository;

/**
 * The type User disability holidays dao test.
 */
class UserDisabilityHolidaysDaoTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "USER_DISABILITY_HOLIDAY";
    private static final String DESCRIPTION = "days because user have some disability";
    private static final int DAY = 20;

    @InjectMocks
    private UserDisabilityHolidaysDao underTest;

    @Mock
    private UserDisabilityHolidayRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUserDisabilityHolidaysShouldReturnListOfEntities() {
        //GIVEN
        List<UserDisabilityHolidaysEntity> expected = createEntities();
        given(repository.findAll()).willReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        List<UserDisabilityHolidaysEntity> actual = underTest.getAllUserDisabilityHolidays();
        //THEN
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<UserDisabilityHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private UserDisabilityHolidaysEntity createEntity() {
        return UserDisabilityHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .build();
    }

    @Test
    void testSaveUserDisabilityHolidayShouldSave() {
        //GIVEN
        UserDisabilityHolidaysEntity holiday = createEntity();

        //WHEN
        verifyNoInteractions(repository);
        underTest.saveUserDisabilityHoliday(holiday);
        //THEN
        verify(repository, times(1)).save(holiday);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteUserDisabilityHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository);
        underTest.deleteUserDisabilityHoliday(HOLIDAY_ID);
        //THEN
        verify(repository, times(1)).deleteById(HOLIDAY_ID);
        verifyNoMoreInteractions(repository);
    }

}
