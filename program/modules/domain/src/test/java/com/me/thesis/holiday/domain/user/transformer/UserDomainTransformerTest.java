package com.me.thesis.holiday.domain.user.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.user.domain.CreateUserDomain;
import com.me.thesis.holiday.domain.user.enums.UserDomainRole;
import com.me.thesis.holiday.dto.user.domain.CreateUserDto;
import com.me.thesis.holiday.dto.user.enums.UserDtoRole;

/**
 * The type User domain transformer test.
 */
class UserDomainTransformerTest {

    private static final String NAME = "Test";
    private static final String USER_EMAIL = "test@test.com";

    @InjectMocks
    private UserDomainTransformer underTest;

    @Mock
    private UserDomainRoleTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserDtoToDomainWithUserRole() {
        //GIVEN
        CreateUserDto createUserDto = createUserDtoWithUserRole();
        CreateUserDomain expected = createUserDomainWithUserRole();
        when(transformer.transform(UserDtoRole.USER)).thenReturn(UserDomainRole.USER);
        //WHEN
        verifyNoInteractions(transformer);
        CreateUserDomain actual = underTest.transform(createUserDto);
        //THEN
        verify(transformer).transform(UserDtoRole.USER);
        verifyNoMoreInteractions(transformer);
        assertEquals(expected, actual);
    }

    private CreateUserDomain createUserDomainWithUserRole() {
        return CreateUserDomain.builder()
            .email(USER_EMAIL)
            .role(UserDomainRole.USER)
            .name(NAME)
            .build();
    }

    private CreateUserDto createUserDtoWithUserRole() {
        return CreateUserDto.builder()
            .email(USER_EMAIL)
            .role(UserDtoRole.USER)
            .name(NAME)
            .build();
    }

}
