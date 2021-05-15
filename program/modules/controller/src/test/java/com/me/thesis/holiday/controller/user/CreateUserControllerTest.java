package com.me.thesis.holiday.controller.user;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.user.domain.CreateUserDomain;
import com.me.thesis.holiday.domain.user.enums.UserDomainRole;
import com.me.thesis.holiday.domain.user.transformer.UserDomainTransformer;
import com.me.thesis.holiday.dto.user.domain.CreateUserDto;
import com.me.thesis.holiday.dto.user.enums.UserDtoRole;
import com.me.thesis.holiday.service.user.services.UserService;

/**
 * The type Create user controller test.
 */
class CreateUserControllerTest {

    private static final String EMAIL = "test@test.com";

    @InjectMocks
    private CreateUserController underTest;

    @Mock
    private UserService userService;

    @Mock
    private UserDomainTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserByAdminShouldCallTransformerAndService() {
        //GIVEN
        InOrder inOrder = inOrder(transformer, userService);
        CreateUserDto createUserDto = createCreateUserDto();
        CreateUserDomain createUserDomain = createCreateUserDomain();

        given(transformer.transform(createUserDto)).willReturn(createUserDomain);
        //WHEN
        verifyNoInteractions(transformer, userService);
        underTest.createUserByAdmin(createUserDto);
        //THEN
        inOrder.verify(transformer, times(1)).transform(createUserDto);
        inOrder.verify(userService, times(1)).createUser(createUserDomain);
        verifyNoMoreInteractions(transformer, userService);
    }

    private CreateUserDto createCreateUserDto() {
        return CreateUserDto.builder()
            .email(EMAIL)
            .role(UserDtoRole.USER)
            .build();
    }

    private CreateUserDomain createCreateUserDomain() {
        return CreateUserDomain.builder()
            .email(EMAIL)
            .role(UserDomainRole.USER)
            .build();
    }
}
