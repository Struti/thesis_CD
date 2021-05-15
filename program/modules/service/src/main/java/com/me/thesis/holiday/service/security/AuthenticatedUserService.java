package com.me.thesis.holiday.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {

    @Autowired
    private DefaultUserDetailsService service;

    public Long getAuthenticatedPersonDetails() {
        Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();
        String name = authentication.getName();
        return service.getUser(name)
            .getPersonEntity()
            .getPersonId();
    }

}
