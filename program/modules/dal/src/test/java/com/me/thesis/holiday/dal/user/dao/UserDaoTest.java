package com.me.thesis.holiday.dal.user.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.enums.PersonEntityEmployeeRole;
import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;
import com.me.thesis.holiday.dal.user.domain.UserEntity;
import com.me.thesis.holiday.dal.user.enums.UserEntityRole;
import com.me.thesis.holiday.dal.user.repository.UserRepository;

/**
 * The type User dao test.
 */
class UserDaoTest {

    private static final String USER_EMAIL = "test@test.com";
    private static final String USER_PASSWORD = "test1234";
    private static final long PERSON_ID = 1L;
    private static final int BIRTH_YEAR = 1992;
    private static final int BIRTH_MONTH = 3;
    private static final int BIRTH_DAY = 4;
    private static final int START_YEAR = 2015;
    private static final int START_MONTH = 7;
    private static final int START_DAY = 17;

    @InjectMocks
    private UserDao underTest;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserShouldCallUserRepository() {
        //GIVEN
        UserEntity userEntity = createUserEntity();
        //WHEN
        verifyNoInteractions(userRepository);
        underTest.createUser(userEntity);
        //THEN
        verify(userRepository).save(userEntity);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testEmailShouldCallRepository() {
        //GIVEN
        //WHEN
        verifyNoInteractions(userRepository);
        underTest.emailExist(USER_EMAIL);
        //THEN
        verify(userRepository).existsByEmail(USER_EMAIL);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testEmailExistShouldReturnTrue() {
        //GIVEN
        given(userRepository.existsByEmail(USER_EMAIL)).willReturn(true);
        //WHEN
        //THEN
        assertTrue(underTest.emailExist(USER_EMAIL));
    }

    @Test
    void testEmailExistShouldReturnFalse() {
        //GIVEN
        given(userRepository.existsByEmail(USER_EMAIL)).willReturn(false);
        //WHEN
        //THEN
        assertFalse(underTest.emailExist(USER_EMAIL));
    }

    @Test
    void testFindAllShouldFindAllUser() {
        //GIVEN
        List<UserEntity> expected = createUserEntities();
        given(userRepository.findAll()).willReturn(expected);
        //WHEN
        verifyNoInteractions(userRepository);
        List<UserEntity> actual = underTest.findAllUser();
        //THEN
        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);
        assertEquals(expected, actual);
    }

    private List<UserEntity> createUserEntities() {
        return List.of(createUserEntity(), createUserEntity());
    }

    private UserEntity createUserEntity() {
        return UserEntity.builder()
            .email(USER_EMAIL)
            .password(USER_PASSWORD)
            .role(UserEntityRole.USER)
            .personEntity(createPersonEntity())
            .build();
    }

    private PersonEntity createPersonEntity() {
        return PersonEntity.builder()
            .personId(PERSON_ID)
            .gender(PersonEntityGender.MALE)
            .birthDate(LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY))
            .disability(false)
            .child(true)
            .startDate(LocalDate.of(START_YEAR, START_MONTH, START_DAY))
            .employeeRole(PersonEntityEmployeeRole.EMPLOYEE)
            .build();
    }

    @Test
    void testFindByEmailShouldReturnEntity() {
        //GIVEN
        UserEntity expected = createUserEntity();
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(userRepository);
        UserEntity actual = underTest.findByEmail(USER_EMAIL);
        //THEN
        verify(userRepository).findByEmail(USER_EMAIL);
        verifyNoMoreInteractions(userRepository);
        assertEquals(expected, actual);
    }

    @Test
    void testUpdateShouldChangePassword() {
        //GIVEN
        UserEntity entity = createUserEntity();
        //WHEN
        verifyNoInteractions(userRepository);
        underTest.update(entity);
        //THEN
        verify(userRepository).save(entity);
        verifyNoMoreInteractions(userRepository);
    }

}
