package com.me.thesis.holiday.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.dto.user.domain.ChangePasswordDto;
import com.me.thesis.holiday.service.user.services.UserService;

/**
 * The type Change password controller.
 */
@RestController
public class ChangePasswordController {

    private static final String CHANGE_PASSWORD = "/changePassword";

    @Autowired
    private UserService service;

    /**
     * Change password.
     *
     * @param changePasswordDto the change password dto
     */
    @PostMapping(CHANGE_PASSWORD)
    public void changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        service.changePassword(changePasswordDto);
    }

}
