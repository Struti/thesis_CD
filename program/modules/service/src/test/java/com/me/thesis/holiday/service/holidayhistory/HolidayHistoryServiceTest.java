package com.me.thesis.holiday.service.holidayhistory;

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

import com.me.thesis.holiday.dal.holidayhistory.HolidayHistoryDao;
import com.me.thesis.holiday.dal.holidayhistory.domain.HolidayHistoryEntity;
import com.me.thesis.holiday.dal.holidayhistory.transformer.HolidayHistoryEntityTransformer;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;

/**
 * The type Holiday history service test.
 */
class HolidayHistoryServiceTest {

    private static final long ID = 11111L;
    private static final String HISTORY = "HISTORY";

    @InjectMocks
    private HolidayHistoryService underTest;

    @Mock
    private HolidayHistoryDao dao;

    @Mock
    private HolidayHistoryEntityTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveShouldSave() {
        //GIVEN
        HolidayHistory domain = createDomain();
        HolidayHistoryEntity entity = createEntity();
        when(transformer.transform(domain)).thenReturn(entity);
        //WHEN
        verifyNoInteractions(transformer, dao);
        underTest.save(domain);
        //THEN
        verify(transformer).transform(domain);
        verify(dao).save(entity);
        verifyNoMoreInteractions(dao, transformer);
    }

    @Test
    void testGetHistoryShouldReturnHistoryById() {
        //GIVEN
        HolidayHistoryEntity entity = createEntity();
        HolidayHistory expected = createDomain();
        when(dao.getHistory(ID)).thenReturn(entity);
        when(transformer.transform(entity)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(transformer, dao);
        HolidayHistory actual = underTest.getHistory(ID);
        //THEN
        verify(dao).getHistory(ID);
        verify(transformer).transform(entity);
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
    }

    @Test
    void testGetDadsHistoriesShouldReturnHistories() {
        //GIVEN
        List<HolidayHistoryEntity> entities = List.of(createEntity());
        List<HolidayHistory> expected = List.of(createDomain());
        when(dao.getDadsHistories()).thenReturn(entities);
        when(transformer.transform(entities.get(0))).thenReturn(expected.get(0));
        //WHEN
        verifyNoInteractions(transformer, dao);
        List<HolidayHistory> actual = underTest.getDadsHistories();
        //THEN
        verify(dao).getDadsHistories();
        verify(transformer).transform(entities.get(0));
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
    }

    private HolidayHistory createDomain() {
        return HolidayHistory.builder()
            .id(ID)
            .person(createPerson())
            .yearHistories(createYearHistory())
            .build();
    }

    private HolidayHistoryEntity createEntity() {
        return HolidayHistoryEntity.builder()
            .holidayHistoryId(ID)
            .personEntity(createPersonEntity())
            .history(HISTORY)
            .build();
    }

    private PersonDetails createPerson() {
        return PersonDetails.builder()
            .build();
    }

    private List<YearHistory> createYearHistory() {
        return List.of(YearHistory.builder()
            .build());
    }

    private PersonEntity createPersonEntity() {
        return PersonEntity.builder()
            .build();
    }

    @Test
    void testGetHistoryByPersonIdShouldReturnHistory() {
        //GIVEN
        HolidayHistoryEntity entity = createEntity();
        HolidayHistory expected = createDomain();
        when(dao.getHistoryByPersonId(ID)).thenReturn(entity);
        when(transformer.transform(entity)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(transformer, dao);
        HolidayHistory actual = underTest.getHistoryByPersonId(ID);
        //THEN
        verify(dao).getHistoryByPersonId(ID);
        verify(transformer).transform(entity);
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
    }

}
