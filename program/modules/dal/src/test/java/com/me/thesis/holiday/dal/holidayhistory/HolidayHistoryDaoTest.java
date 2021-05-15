package com.me.thesis.holiday.dal.holidayhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidayhistory.domain.HolidayHistoryEntity;
import com.me.thesis.holiday.dal.holidayhistory.repository.HolidayHistoryRepository;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;

/**
 * The type Holiday history dao test.
 */
class HolidayHistoryDaoTest {

    private static final long HISTORY_ID = 11111L;

    @InjectMocks
    private HolidayHistoryDao underTest;

    @Mock
    private HolidayHistoryRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveShouldSave() {
        //GIVEN
        HolidayHistoryEntity entity = createEntity();
        //WHEN
        verifyNoInteractions(repository);
        underTest.save(entity);
        //THEN
        verify(repository).save(entity);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testGetHistoryShouldReturnEntity() {
        //GIVEN
        HolidayHistoryEntity expected = createEntity();
        when(repository.getOne(HISTORY_ID)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        HolidayHistoryEntity actual = underTest.getHistory(HISTORY_ID);
        //THEN
        verify(repository).getOne(HISTORY_ID);
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
    }

    private HolidayHistoryEntity createEntity() {
        return HolidayHistoryEntity.builder()
            .holidayHistoryId(HISTORY_ID)
            .build();
    }

    @Test
    void testGetDadsHistoriesShouldReturnEntities() {
        //GIVEN
        PersonEntity personEntity = createPersonEntity();
        HolidayHistoryEntity entity = createEntity();
        personEntity.setHolidayHistoryEntity(entity);
        entity.setPersonEntity(personEntity);
        List<HolidayHistoryEntity> expected = List.of(entity);
        when(repository.findAllByPersonEntity_Gender(PersonEntityGender.MALE)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        List<HolidayHistoryEntity> actual = underTest.getDadsHistories();
        //THEN
        verify(repository).findAllByPersonEntity_Gender(PersonEntityGender.MALE);
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
    }

    private PersonEntity createPersonEntity() {
        return PersonEntity.builder()
            .child(true)
            .build();
    }

    @Test
    void testHistoryByPersonIDShouldReturnHistory() {
        //GIVEN
        HolidayHistoryEntity expected = createEntity();
        when(repository.findByPersonEntity_PersonId(HISTORY_ID)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        HolidayHistoryEntity actual = underTest.getHistoryByPersonId(HISTORY_ID);
        //THEN
        verify(repository).findByPersonEntity_PersonId(HISTORY_ID);
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
    }

}
