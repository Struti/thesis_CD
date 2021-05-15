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

import com.me.thesis.holiday.dal.holidaytypes.age.dao.AgeBasedHolidaysDao;
import com.me.thesis.holiday.dal.holidaytypes.age.domain.AgeBasedHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.age.transformer.entitytransformer.AgeBasedHolidaysEntityTransfer;
import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;

/**
 * The type Age holidays service test.
 */
class AgeHolidaysServiceTest {

    private static final int AGE = 1;
    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "AGE_HOLIDAY";
    private static final String DESCRIPTION = "days because of user age";
    private static final int DAYS = 20;

    @InjectMocks
    private AgeHolidaysService underTest;

    @Mock
    private AgeBasedHolidaysDao dao;

    @Mock
    private AgeBasedHolidaysEntityTransfer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAgeBasedHolidayDetailsShouldReturnList() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        List<AgeBasedHolidaysEntity> entities = createEntities();
        List<AgeBasedHolidays> expected = createDomains();
        given(dao.getAgeBasedHolidays()).willReturn(entities);
        given(transformer.transform(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(dao, transformer);
        List<AgeBasedHolidays> actual = underTest.getAllAgeBasedHolidayDetails();
        //THEN
        inOrder.verify(dao, times(1))
            .getAgeBasedHolidays();
        inOrder.verify(transformer, times(1))
            .transform(entities);
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<AgeBasedHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<AgeBasedHolidays> createDomains() {
        return List.of(createDomain());
    }

    private AgeBasedHolidaysEntity createEntity() {
        return AgeBasedHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .age(AGE)
            .day(DAYS)
            .build();
    }

    private AgeBasedHolidays createDomain() {
        return AgeBasedHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .age(AGE)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveAgeBasedHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        AgeBasedHolidaysEntity entity = createEntity();
        AgeBasedHolidays domain = createDomain();
        given(transformer.transform(domain)).willReturn(entity);
        //WHEN
        verifyNoInteractions(dao, transformer);
        underTest.saveAgeBasedHoliday(domain);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(domain);
        inOrder.verify(dao, times(1))
            .saveAgeBasedHoliday(entity);
        verifyNoMoreInteractions(dao, transformer);
    }

    @Test
    void testDeleteAgeBasedHolidayDetailsShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(dao);
        underTest.deleteAgeBasedHolidayDetails(HOLIDAY_ID);
        //THEN
        verify(dao, times(1)).deleteAgeBasedHoliday(HOLIDAY_ID);
        verifyNoMoreInteractions(dao);
    }

}
