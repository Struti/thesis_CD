package com.me.thesis.holiday.domain.user.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.user.enums.UserDomainRole;
import com.me.thesis.holiday.dto.user.enums.UserDtoRole;

/**
 * The type User domain role transformer test.
 */
class UserDomainRoleTransformerTest {

    @InjectMocks
    private UserDomainRoleTransformer underTest;

    @Mock
    private Map<UserDtoRole, UserDomainRole> userRoleMap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldUser() {
        //GIVEN
        when(userRoleMap.get(UserDtoRole.USER)).thenReturn(UserDomainRole.USER);
        //WHEN
        verifyNoInteractions(userRoleMap);
        UserDomainRole actual = underTest.transform(UserDtoRole.USER);
        //THEN
        verify(userRoleMap).get(UserDtoRole.USER);
        verifyNoMoreInteractions(userRoleMap);
        assertEquals(UserDomainRole.USER, actual);
    }

    @Test
    void testTransformShouldAdmin() {
        //GIVEN
        when(userRoleMap.get(UserDtoRole.ADMIN)).thenReturn(UserDomainRole.ADMIN);
        //WHEN
        verifyNoInteractions(userRoleMap);
        UserDomainRole actual = underTest.transform(UserDtoRole.ADMIN);
        //THEN
        verify(userRoleMap).get(UserDtoRole.ADMIN);
        verifyNoMoreInteractions(userRoleMap);
        assertEquals(UserDomainRole.ADMIN, actual);
    }

}
