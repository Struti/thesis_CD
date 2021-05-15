package com.me.thesis.holiday.dal.user.transformer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.user.enums.UserEntityRole;
import com.me.thesis.holiday.domain.user.enums.UserDomainRole;

/**
 * The type User entity role transformer.
 */
@Component
public class UserEntityRoleTransformer {

    @Autowired
    private Map<UserEntityRole, UserDomainRole> userRoleEntityToDomainMap;

    @Autowired
    private Map<UserDomainRole, UserEntityRole> userRoleDomainToEntityMap;

    /**
     * Transform user entity role.
     *
     * @param role the role
     *
     * @return the user entity role
     */
    public UserEntityRole transform(UserDomainRole role) {
        return userRoleDomainToEntityMap.getOrDefault(role, UserEntityRole.USER);
    }

    /**
     * Transform user domain role.
     *
     * @param role the role
     *
     * @return the user domain role
     */
    public UserDomainRole transform(UserEntityRole role) {
        return userRoleEntityToDomainMap.getOrDefault(role, UserDomainRole.USER);
    }

}
