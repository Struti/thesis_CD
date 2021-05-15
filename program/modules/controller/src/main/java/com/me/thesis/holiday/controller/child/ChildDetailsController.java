package com.me.thesis.holiday.controller.child;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.child.transformer.ChildDetailsTransformer;
import com.me.thesis.holiday.dto.child.ChildDetailsDto;
import com.me.thesis.holiday.service.child.ChildService;

/**
 * The type Child details controller.
 */
@RestController
public class ChildDetailsController {

    private static final String ADMIN_CHILD_SELECT_PERSON = "/admin/child/personChildDetails";
    private static final String ADMIN_CHILD_SAVE_CHILDREN = "/admin/child/saveChild";
    private static final String ADMIN_CHILD_DELETE_CHILD = "/admin/child/deleteChild/{id}";

    @Autowired
    private ChildService service;

    @Autowired
    private ChildDetailsTransformer transformer;

    /**
     * Gets child details.
     *
     * @param personId the person id
     *
     * @return the child details
     */
    @GetMapping(ADMIN_CHILD_SELECT_PERSON)
    public List<ChildDetails> getChildDetails(Long personId) {
        return service.getAllChildrenByPersonId(personId);
    }

    /**
     * Save child details.
     *
     * @param childDetails the child details
     */
    @PostMapping(ADMIN_CHILD_SAVE_CHILDREN)
    public void saveChildDetails(@RequestBody ChildDetailsDto childDetails) {
        service.saveChild(transformer.transform(childDetails), childDetails.getPerson());
    }

    /**
     * Delete child details.
     *
     * @param childId the child id
     */
    @DeleteMapping(ADMIN_CHILD_DELETE_CHILD)
    public void deleteChildDetails(@PathVariable("id") Long childId) {
        service.deleteChild(childId);
    }

}
