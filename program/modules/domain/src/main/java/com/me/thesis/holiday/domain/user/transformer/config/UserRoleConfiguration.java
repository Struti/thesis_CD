package com.me.thesis.holiday.domain.user.transformer.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.me.thesis.holiday.domain.user.enums.UserDomainRole;
import com.me.thesis.holiday.dto.user.enums.UserDtoRole;

/**
 * The type User role configuration.
 */
@Configuration
public class UserRoleConfiguration {

    /**
     * User role map map.
     *
     * @return the map
     */
    @Bean
    public Map<UserDtoRole, UserDomainRole> userRoleMap() {
        return Map.ofEntries(
            Map.entry(UserDtoRole.USER, UserDomainRole.USER),
            Map.entry(UserDtoRole.ADMIN, UserDomainRole.ADMIN)
        );
    }

}
