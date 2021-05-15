package com.me.thesis.holiday.domain.user.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.user.domain.CreateUserDomain;
import com.me.thesis.holiday.dto.user.domain.CreateUserDto;

/**
 * The type User domain transformer.
 */
@Component
public class UserDomainTransformer {

    @Autowired
    private UserDomainRoleTransformer transformer;

    /**
     * Transform create user domain.
     *
     * @param createUserDto the create user dto
     *
     * @return the create user domain
     */
    public CreateUserDomain transform(CreateUserDto createUserDto) {
        return CreateUserDomain.builder()
            .email(createUserDto.getEmail())
            .role(transformer.transform(createUserDto.getRole()))
            .name(createUserDto.getName())
            .build();
    }

}
