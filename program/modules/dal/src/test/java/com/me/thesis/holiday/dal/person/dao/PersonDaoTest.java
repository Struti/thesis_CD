package com.me.thesis.holiday.dal.person.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.enums.PersonEntityEmployeeRole;
import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;
import com.me.thesis.holiday.dal.person.enums.PersonEntityTitle;
import com.me.thesis.holiday.dal.person.repository.PersonRepository;

/**
 * The type Person dao test.
 */
class PersonDaoTest {

    private static final long TEST_PERSON_1_ID = 9181L;
    private static final String ACTUAL_LOCATION = "Románia, Nagyvárad";
    private static final String TEST_PERSON_1_NAME = "Ananth Zapatero";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1973, 5, 6);
    private static final LocalDate START_DATE_AT_WORK = LocalDate.of(2014, 10, 3);

    @InjectMocks
    private PersonDao underTest;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByIdShouldCallRepository() {
        //GIVEN
        PersonEntity expected = createPersonEntity();
        given(repository.findById(TEST_PERSON_1_ID)).willReturn(Optional.ofNullable(expected));
        //WHEN
        verifyNoInteractions(repository);
        PersonEntity actual = underTest.findById(TEST_PERSON_1_ID);
        //THEN
        verify(repository, times(1)).findById(TEST_PERSON_1_ID);
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void testSavePersonShouldCallRepositoryAndReturnEntity() {
        //GIVEN
        PersonEntity expected = createPersonEntity();

        given(repository.findById(TEST_PERSON_1_ID)).willReturn(Optional.ofNullable(expected));
        //WHEN
        verifyNoInteractions(repository);
        PersonEntity actual = underTest.savePerson(expected);
        //THEN
        verify(repository, times(1)).save(expected);
        verify(repository, times(1)).findById(TEST_PERSON_1_ID);
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void testGetAllPersonDetailsShouldCallRepositoryAndReturnListOfPersonDetails() {
        //GIVEN
        List<PersonEntity> expected = createPersonEntityList();
        given(repository.findAll()).willReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        List<PersonEntity> actual = underTest.findAllPersonDetails();
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void testDeletePersonShouldCallRepository2TimesAndReturnWithEntity() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(repository, repository);
        PersonEntity expected = createPersonEntity();
        given(repository.findById(TEST_PERSON_1_ID)).willReturn(Optional.ofNullable(expected));
        //WHEN
        verifyNoInteractions(repository);
        PersonEntity actual = underTest.deletePerson(TEST_PERSON_1_ID);
        //THEN
        inOrder.verify(repository, times(1)).findById(TEST_PERSON_1_ID);
        inOrder.verify(repository, times(1)).delete(expected);
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<PersonEntity> createPersonEntityList() {
        return List.of(createPersonEntity());
    }

    private PersonEntity createPersonEntity() {
        return PersonEntity.builder()
            .personId(TEST_PERSON_1_ID)
            .title(PersonEntityTitle.NONE)
            .fullName(TEST_PERSON_1_NAME)
            .birthName(TEST_PERSON_1_NAME)
            .gender(PersonEntityGender.FEMALE)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .child(false)
            .startDate(START_DATE_AT_WORK)
            .lastDay(LocalDate.MAX)
            .employeeRole(PersonEntityEmployeeRole.SUPERVISOR)
            .actualLocation(ACTUAL_LOCATION)
            .build();
    }

}
