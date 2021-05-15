package com.me.thesis.holiday.service.user.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.user.domain.CreateUserDomain;
import com.me.thesis.holiday.domain.user.domain.CreateUserHelper;
import com.me.thesis.holiday.service.lib.CurrentDateProvider;

/**
 * The type Create user factory.
 */
@Component
public class CreateUserFactory {

    @Autowired
    private CurrentDateProvider currentDateProvider;

    /**
     * Create create user helper.
     *
     * @param createUserDomain the create user domain
     * @param firstPassword    the first password
     *
     * @return the create user helper
     */
    public CreateUserHelper create(CreateUserDomain createUserDomain, String firstPassword) {
        return CreateUserHelper.builder()
            .user(createUserDomain)
            .password(firstPassword)
            .currentDate(currentDateProvider.provide())
            .build();
    }

}
