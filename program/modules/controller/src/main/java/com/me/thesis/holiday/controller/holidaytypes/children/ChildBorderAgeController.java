package com.me.thesis.holiday.controller.holidaytypes.children;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildBorderAge;
import com.me.thesis.holiday.service.holidaytypes.ChildBorderAgeService;

/**
 * The type Child border age controller.
 */
@RestController
public class ChildBorderAgeController {

    private static final String ADMIN_HOLIDAYS_CHILDREN_GET_BORDER_AGE = "/admin/holidays/children/getBorderAge";
    private static final String ADMIN_HOLIDAYS_CHILDREN_SAVE_BORDER_AGE = "/admin/holidays/children/saveBorderAge";
    private static final String ADMIN_HOLIDAYS_CHILDREN_DELETE_BORDER_AGE = "/admin/holidays/children/deleteBorderAge/{id}";

    @Autowired
    private ChildBorderAgeService service;

    /**
     * Gets child border age.
     *
     * @return the child border age
     */
    @GetMapping(ADMIN_HOLIDAYS_CHILDREN_GET_BORDER_AGE)
    public ChildBorderAge getChildBorderAge() {
        return service.getChildBorderAge();
    }

    /**
     * Save child border age.
     *
     * @param childBorderAge the child border age
     */
    @PostMapping(ADMIN_HOLIDAYS_CHILDREN_SAVE_BORDER_AGE)
    public void saveChildBorderAge(@RequestBody ChildBorderAge childBorderAge) {
        service.saveChildBorderAge(childBorderAge);
    }

    /**
     * Delete child border age.
     *
     * @param id the id
     */
    @DeleteMapping(ADMIN_HOLIDAYS_CHILDREN_DELETE_BORDER_AGE)
    public void deleteChildBorderAge(@PathVariable("id") Long id) {
        service.deleteChildBorderAge(id);
    }

}
