package com.me.thesis.holiday.dal.user.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
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

import com.me.thesis.holiday.dal.user.enums.UserEntityRole;
import com.me.thesis.holiday.domain.user.enums.UserDomainRole;

/**
 * The type User entity role transformer test.
 */
class UserEntityRoleTransformerTest {

    private static final UserDomainRole USER_DOMAIN_ROLE = UserDomainRole.USER;
    private static final UserEntityRole USER_ENTITY_ROLE = UserEntityRole.USER;

    @InjectMocks
    private UserEntityRoleTransformer underTest;

    @Mock
    private Map<UserEntityRole, UserDomainRole> userRoleEntityToDomainMap;

    @Mock
    private Map<UserDomainRole, UserEntityRole> userRoleDomainToEntityMap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformEntityToDomain() {
        //GIVEN
        when(userRoleEntityToDomainMap.getOrDefault(USER_ENTITY_ROLE, USER_DOMAIN_ROLE)).thenReturn(USER_DOMAIN_ROLE);
        //WHEN
        verifyNoInteractions(userRoleEntityToDomainMap);
        UserDomainRole actual = underTest.transform(USER_ENTITY_ROLE);
        //THEN
        verify(userRoleEntityToDomainMap, times(1)).getOrDefault(USER_ENTITY_ROLE, USER_DOMAIN_ROLE);
        verifyNoMoreInteractions(userRoleEntityToDomainMap);
        assertEquals(USER_DOMAIN_ROLE, actual);
    }

    @Test
    void testTestTransformShouldTransformDomainToEntity() {
        //GIVEN
        when(userRoleDomainToEntityMap.getOrDefault(USER_DOMAIN_ROLE, USER_ENTITY_ROLE))
            .thenReturn(USER_ENTITY_ROLE);
        //WHEN
        verifyNoInteractions(userRoleDomainToEntityMap);
        UserEntityRole actual = underTest.transform(USER_DOMAIN_ROLE);
        //THEN
        verify(userRoleDomainToEntityMap, times(1)).getOrDefault(USER_DOMAIN_ROLE, USER_ENTITY_ROLE);
        verifyNoMoreInteractions(userRoleDomainToEntityMap);
        assertEquals(USER_ENTITY_ROLE, actual);
    }

}
