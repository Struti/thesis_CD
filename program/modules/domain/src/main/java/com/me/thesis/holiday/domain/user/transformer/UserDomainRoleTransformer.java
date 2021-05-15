package com.me.thesis.holiday.domain.user.transformer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.user.enums.UserDomainRole;
import com.me.thesis.holiday.dto.user.enums.UserDtoRole;

/**
 * The type User domain role transformer.
 */
@Component
public class UserDomainRoleTransformer {

    @Autowired
    private Map<UserDtoRole, UserDomainRole> userRoleMap;

    /**
     * Transform user domain role.
     *
     * @param userDtoRole the user dto role
     *
     * @return the user domain role
     */
    public UserDomainRole transform(UserDtoRole userDtoRole) {
        return userRoleMap.get(userDtoRole);
    }

}
