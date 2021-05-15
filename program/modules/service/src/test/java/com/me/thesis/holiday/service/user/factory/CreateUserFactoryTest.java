package com.me.thesis.holiday.service.user.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.user.domain.CreateUserDomain;
import com.me.thesis.holiday.domain.user.domain.CreateUserHelper;
import com.me.thesis.holiday.domain.user.enums.UserDomainRole;
import com.me.thesis.holiday.service.lib.CurrentDateProvider;

class CreateUserFactoryTest {

    private static final LocalDate CURRENT_DATE = LocalDate.EPOCH;
    private static final String FIRST_PASSWORD = "password";
    private static final UserDomainRole USER_DOMAIN_ROLE = UserDomainRole.USER;
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    @InjectMocks
    private CreateUserFactory underTest;

    @Mock
    private CurrentDateProvider currentDateProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateShouldCreate() {
        //GIVEN
        CreateUserDomain domain = createDomain();
        CreateUserHelper expected = createHelper(domain);
        when(currentDateProvider.provide()).thenReturn(CURRENT_DATE);
        //WHEN
        verifyNoInteractions(currentDateProvider);
        CreateUserHelper actual = underTest.create(domain, FIRST_PASSWORD);
        //THEN
        verify(currentDateProvider).provide();
        verifyNoMoreInteractions(currentDateProvider);
        assertEquals(expected, actual);
    }

    private CreateUserDomain createDomain() {
        return CreateUserDomain.builder()
            .email(EMAIL)
            .name(NAME)
            .role(USER_DOMAIN_ROLE)
            .build();
    }

    private CreateUserHelper createHelper(CreateUserDomain domain) {
        return CreateUserHelper.builder()
            .password(FIRST_PASSWORD)
            .user(domain)
            .currentDate(CURRENT_DATE)
            .build();
    }

}
