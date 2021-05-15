package com.me.thesis.holiday.dal.user.transformer.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.me.thesis.holiday.dal.user.enums.UserEntityRole;
import com.me.thesis.holiday.domain.user.enums.UserDomainRole;

/**
 * The type User role configuration.
 */
@Configuration
public class UserRoleEntityConfiguration {

    /**
     * User role entity to domain map map.
     *
     * @return the map
     */
    @Bean
    public Map<UserEntityRole, UserDomainRole> userRoleEntityToDomainMap() {
        return Map.ofEntries(
            Map.entry(UserEntityRole.ADMIN, UserDomainRole.ADMIN),
            Map.entry(UserEntityRole.USER, UserDomainRole.USER)
        );
    }

    /**
     * User role domain to entity map map.
     *
     * @return the map
     */
    @Bean
    public Map<UserDomainRole, UserEntityRole> userRoleDomainToEntityMap() {
        return Map.ofEntries(
            Map.entry(UserDomainRole.ADMIN, UserEntityRole.ADMIN),
            Map.entry(UserDomainRole.USER, UserEntityRole.USER)
        );
    }

}
