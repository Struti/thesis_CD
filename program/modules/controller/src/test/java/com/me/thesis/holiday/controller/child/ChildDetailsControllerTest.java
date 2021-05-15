package com.me.thesis.holiday.controller.child;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.child.transformer.ChildDetailsTransformer;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.dto.child.ChildDetailsDto;
import com.me.thesis.holiday.service.child.ChildService;

/**
 * The type Child details controller test.
 */
class ChildDetailsControllerTest {

    private static final long PERSON_ID = 33333L;
    private static final Long CHILD_ID_1 = 11111L;
    private static final LocalDate BIRTH_DATE = LocalDate.of(2021, 1, 25);
    private static final Long CHILD_ID_2 = 22222L;
    private static final LocalDate EXPECTED_DATE = LocalDate.of(2021, 1, 25);
    private static final Long CHILD_ID_3 = 444444L;
    private static final LocalDate DISABILITY_CERT_EXPIRATION_DATE = LocalDate.of(2021, 1, 30);

    @InjectMocks
    private ChildDetailsController underTest;

    @Mock
    private ChildService service;

    @Mock
    private ChildDetailsTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetChildDetailsShouldReturnWithChildDetails() {
        //GIVEN
        List<ChildDetails> expected = createChildDetailsDomain();

        when(service.getAllChildrenByPersonId(PERSON_ID)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(service);
        List<ChildDetails> actual = underTest.getChildDetails(PERSON_ID);
        //THEN
        verify(service, times(1))
            .getAllChildrenByPersonId(PERSON_ID);
        verifyNoMoreInteractions(service);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<ChildDetails> createChildDetailsDomain() {
        PersonDetails person = createPersonDomain();
        return List.of(createBornChild(person), createExpectedChild(person), createBornChildWithDisability(person));
    }

    private PersonDetails createPersonDomain() {
        return PersonDetails.builder()
            .build();
    }

    private ChildDetails createBornChild(PersonDetails person) {
        return ChildDetails.builder()
            .childId(CHILD_ID_1)
            .person(person)
            .born(true)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .build();
    }

    private ChildDetails createExpectedChild(PersonDetails person) {
        return ChildDetails.builder()
            .childId(CHILD_ID_2)
            .person(person)
            .born(false)
            .expectedDate(EXPECTED_DATE)
            .disability(false)
            .build();
    }

    private ChildDetails createBornChildWithDisability(PersonDetails person) {
        return ChildDetails.builder()
            .childId(CHILD_ID_3)
            .person(person)
            .born(true)
            .birthDate(BIRTH_DATE)
            .disability(true)
            .disabilityCertExpirationDate(DISABILITY_CERT_EXPIRATION_DATE)
            .build();
    }

    @Test
    void testDeleteChildDetailsShouldReturnChildDetailsDto() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.deleteChildDetails(CHILD_ID_1);
        //THEN
        verify(service, times(1))
            .deleteChild(CHILD_ID_1);
        verifyNoMoreInteractions(service);
    }

    @Test
    void testSaveChildDetailsShouldReturnChildDetailsDto() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(service, transformer);
        ChildDetails domain = createBornChild(createPersonDomain());
        ChildDetailsDto dto = createBornChildDtoToSave();

        when(transformer.transform(dto)).thenReturn(domain);
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.saveChildDetails(dto);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(dto);
        inOrder.verify(service, times(1))
            .saveChild(domain, PERSON_ID);
        verifyNoMoreInteractions(service, transformer);
    }

    private ChildDetailsDto createBornChildDtoToSave() {
        return ChildDetailsDto.builder()
            .person(PERSON_ID)
            .born(true)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .build();
    }

}
