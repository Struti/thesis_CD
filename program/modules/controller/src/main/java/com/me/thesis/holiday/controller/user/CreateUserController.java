package com.me.thesis.holiday.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.user.transformer.UserDomainTransformer;
import com.me.thesis.holiday.dto.user.domain.CreateUserDto;
import com.me.thesis.holiday.service.user.services.UserService;

/**
 * The type Create user controller.
 */
@RestController
public class CreateUserController {

    private static final String CREATE_USER_BY_ADMIN = "/admin/user/create";

    @Autowired
    private UserService service;

    @Autowired
    private UserDomainTransformer transformer;

    /**
     * Create user by admin.
     *
     * @param createUserDto the create user dto
     */
    @PostMapping(CREATE_USER_BY_ADMIN)
    public void createUserByAdmin(@RequestBody CreateUserDto createUserDto) {
        service.createUser(transformer.transform(createUserDto));
    }

}
