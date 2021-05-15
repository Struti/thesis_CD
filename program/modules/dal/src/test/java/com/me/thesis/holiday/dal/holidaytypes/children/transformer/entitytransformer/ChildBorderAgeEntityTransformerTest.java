package com.me.thesis.holiday.dal.holidaytypes.children.transformer.entitytransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.children.domain.ChildBorderAgeEntity;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildBorderAge;

/**
 * The type Child border age entity transformer test.
 */
class ChildBorderAgeEntityTransformerTest {

    private static final long CHILD_AGE_BORDER_ID = 11111L;
    private static final int BORDER_AGE = 16;

    @InjectMocks
    private ChildBorderAgeEntityTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformEntity() {
        //GIVEN
        ChildBorderAgeEntity childBorderAgeEntity = createChildBorderAgeEntity();
        ChildBorderAge expected = createChildBorderAgeDomain();
        //WHEN
        ChildBorderAge actual = underTest.transform(childBorderAgeEntity);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void testTransformShouldTransformDomain() {
        //GIVEN
        ChildBorderAge domain = createChildBorderAgeDomain();
        ChildBorderAgeEntity expected = createChildBorderAgeEntity();
        //WHEN
        ChildBorderAgeEntity actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
    }

    private ChildBorderAgeEntity createChildBorderAgeEntity() {
        return ChildBorderAgeEntity.builder()
            .childAgeBorderId(CHILD_AGE_BORDER_ID)
            .borderAge(BORDER_AGE)
            .build();
    }

    private ChildBorderAge createChildBorderAgeDomain() {
        return ChildBorderAge.builder()
            .id(CHILD_AGE_BORDER_ID)
            .childBorderAge(BORDER_AGE)
            .build();
    }

}
