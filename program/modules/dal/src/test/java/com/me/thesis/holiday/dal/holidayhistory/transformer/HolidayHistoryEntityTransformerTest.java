package com.me.thesis.holiday.dal.holidayhistory.transformer;

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
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.transformer.facade.PersonEntityTransformerFacade;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;

/**
 * The type Holiday history entity transformer test.
 */
class HolidayHistoryEntityTransformerTest {

    private static final long ID = 1111L;
    private static final String HISTORY = "history";

    @InjectMocks
    private HolidayHistoryEntityTransformer underTest;

    @Mock
    private YearHistoryTransformer yearHistoryTransformer;

    @Mock
    private PersonEntityTransformerFacade personEntityTransformerFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTestTransformShouldTransform() {
        //GIVEN
        HolidayHistory domain = createDomain();
        HolidayHistoryEntity expected = createEntity();
        when(yearHistoryTransformer.transform(domain.getYearHistories())).thenReturn(HISTORY);
        when(personEntityTransformerFacade.transformFullDomainDetailsToEntity(domain.getPerson())).thenReturn(expected.getPersonEntity());
        //WHEN
        verifyNoInteractions(yearHistoryTransformer, personEntityTransformerFacade);
        HolidayHistoryEntity actual = underTest.transform(domain);
        //THEN
        verify(personEntityTransformerFacade).transformFullDomainDetailsToEntity(domain.getPerson());
        verify(yearHistoryTransformer).transform(domain.getYearHistories());
        verifyNoMoreInteractions(yearHistoryTransformer, personEntityTransformerFacade);
        assertEquals(expected, actual);
    }

    @Test
    void testTestTransformShouldTransformEntity() {
        //GIVEN
        HolidayHistoryEntity entity = createEntity();
        HolidayHistory expected = createDomain();
        when(yearHistoryTransformer.transform(entity.getHistory())).thenReturn(createYearHistories());
        when(personEntityTransformerFacade.transformFullEntityDetailsToDomain(entity.getPersonEntity())).thenReturn(expected.getPerson());
        //WHEN
        verifyNoInteractions(yearHistoryTransformer, personEntityTransformerFacade);
        HolidayHistory actual = underTest.transform(entity);
        //THEN
        verify(personEntityTransformerFacade).transformFullEntityDetailsToDomain(entity.getPersonEntity());
        verify(yearHistoryTransformer).transform(entity.getHistory());
        verifyNoMoreInteractions(yearHistoryTransformer, personEntityTransformerFacade);
        assertEquals(expected, actual);
    }

    private HolidayHistory createDomain() {
        return HolidayHistory.builder()
            .id(ID)
            .person(createPerson())
            .yearHistories(createYearHistories())
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

    private List<YearHistory> createYearHistories() {
        return List.of(createYearHistory());
    }

    private PersonEntity createPersonEntity() {
        return PersonEntity.builder()
            .build();
    }

    private YearHistory createYearHistory() {
        return YearHistory.builder()
            .build();
    }

}
