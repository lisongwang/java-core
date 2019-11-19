package com.lisong.learn.core.annotations;

import com.lisong.learn.core.annotations.atunit.Test;
import com.lisong.learn.core.annotations.atunit.TestObjectCreate;
import com.lisong.learn.core.annotations.atunit.TestProperty;

/**
 * Test the AnUnitRemover function.
 */
public class Exercise11 {

    @TestProperty
    private String firstName;
    private String lastName;

    public Exercise11(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    private String totalName() {
        return firstName + " " + lastName;
    }

    @TestObjectCreate
    static Exercise11 init() {
        return new Exercise11("Steve","Bob");
    }
    @Test
    void t_checkName() {
        assert totalName().equals("Steve Bob");
    }
}