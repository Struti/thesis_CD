package com.me.thesis.holiday.service.user.services;

import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.user.dao.UserDao;
import com.me.thesis.holiday.dal.user.domain.UserEntity;
import com.me.thesis.holiday.dal.user.enums.UserEntityRole;
import com.me.thesis.holiday.dal.user.transformer.UserEntityTransformer;
import com.me.thesis.holiday.domain.user.domain.CreateUserDomain;
import com.me.thesis.holiday.domain.user.domain.CreateUserHelper;
import com.me.thesis.holiday.domain.user.enums.UserDomainRole;
import com.me.thesis.holiday.dto.user.domain.ChangePasswordDto;
import com.me.thesis.holiday.service.password.PasswordGenerator;
import com.me.thesis.holiday.service.user.exceptions.EmailExistException;
import com.me.thesis.holiday.service.user.exceptions.PasswordChangeFailedException;
import com.me.thesis.holiday.service.user.factory.CreateUserFactory;

/**
 * The type User service test.
 */
class UserServiceTest {

    private static final String EMAIL = "test@test.com";
    private static final String PASSWORD = "Test1234";

    @InjectMocks
    private UserService underTest;

    @Mock
    private UserDao userDao;

    @Mock
    private UserEntityTransformer userEntityTransformer;

    @Mock
    private CreateUserFactory createUserFactory;

    @Mock
    private PasswordGenerator passwordGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserShouldThrowEmailExistException() {
        //GIVEN
        CreateUserDomain createUserDomain = createUserDomainWithoutPassword();
        given(userDao.emailExist(EMAIL)).willReturn(true);
        //WHEN
        //THEN
        assertThrows(EmailExistException.class, () -> underTest.createUser(createUserDomain));
    }

    @Test
    void testCreateUserShouldCallPasswordGeneratorTransformerUserDaoInOrder() {
        //GIVEN
        InOrder inOrder = inOrder(userDao, passwordGenerator, userEntityTransformer, userDao, createUserFactory);
        CreateUserDomain createUserDomain = createUserDomainWithoutPassword();
        CreateUserHelper userHelper = createUserHelper(createUserDomain);
        UserEntity userEntity = createUserEntity();

        given(userDao.emailExist(EMAIL)).willReturn(false);
        given(passwordGenerator.createFirstPassword()).willReturn(PASSWORD);
        given(createUserFactory.create(createUserDomain, PASSWORD)).willReturn(userHelper);
        given(userEntityTransformer.transform(userHelper)).willReturn(userEntity);
        //WHEN
        verifyNoInteractions(userDao, passwordGenerator, userEntityTransformer, userDao, createUserFactory);
        underTest.createUser(createUserDomain);
        //THEN
        inOrder.verify(userDao)
            .emailExist(EMAIL);
        inOrder.verify(passwordGenerator)
            .createFirstPassword();
        inOrder.verify(createUserFactory)
            .create(createUserDomain, PASSWORD);
        inOrder.verify(userEntityTransformer)
            .transform(userHelper);
        inOrder.verify(userDao)
            .createUser(userEntity);
        verifyNoMoreInteractions(userDao, passwordGenerator, userEntityTransformer, userDao, createUserFactory);
    }

    private CreateUserHelper createUserHelper(CreateUserDomain createUserDomain) {
        return CreateUserHelper.builder()
            .currentDate(LocalDate.EPOCH)
            .user(createUserDomain)
            .password(PASSWORD)
            .build();
    }

    private CreateUserDomain createUserDomainWithoutPassword() {
        return CreateUserDomain.builder()
            .email(EMAIL)
            .role(UserDomainRole.USER)
            .build();
    }

    private UserEntity createUserEntity() {
        return UserEntity.builder()
            .email(EMAIL)
            .password(PASSWORD)
            .role(UserEntityRole.USER)
            .build();
    }

    @Test
    void testChangePasswordShouldChangePWD() {
        //GIVEN
        ChangePasswordDto dto = createDto();
        UserEntity userEntity = createUserEntity();
        when(userDao.findByEmail(EMAIL)).thenReturn(userEntity);
        //WHEN
        verifyNoInteractions(userDao, passwordGenerator, userEntityTransformer, userDao, createUserFactory);
        underTest.changePassword(dto);
        //THEN
        verify(userDao).findByEmail(EMAIL);
        verify(userDao).update(userEntity);
        verifyNoMoreInteractions(userDao, passwordGenerator, userEntityTransformer, userDao, createUserFactory);
    }

    private ChangePasswordDto createDto() {
        return ChangePasswordDto.builder()
            .email(EMAIL)
            .newPassword(PASSWORD)
            .oldPassword(PASSWORD)
            .build();
    }

    @Test
    void testChangePasswordShouldThrow() {
        //GIVEN
        ChangePasswordDto dto = createDtoDif();
        UserEntity userEntity = createUserEntity();
        when(userDao.findByEmail(EMAIL)).thenReturn(userEntity);
        //WHEN
        verifyNoInteractions(userDao, passwordGenerator, userEntityTransformer, userDao, createUserFactory);
        assertThrows(PasswordChangeFailedException.class, () -> underTest.changePassword(dto));
        //THEN
        verify(userDao).findByEmail(EMAIL);
        verifyNoMoreInteractions(userDao, passwordGenerator, userEntityTransformer, userDao, createUserFactory);
    }

    private ChangePasswordDto createDtoDif() {
        return ChangePasswordDto.builder()
            .email(EMAIL)
            .newPassword(PASSWORD)
            .oldPassword(PASSWORD + 1)
            .build();
    }

}
