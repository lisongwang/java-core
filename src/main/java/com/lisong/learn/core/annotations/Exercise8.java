package com.lisong.learn.core.annotations;

import com.lisong.learn.core.annotations.atunit.Test;
import com.lisong.learn.core.annotations.atunit.TestProperty;

public class Exercise8 extends Test8 {

    @Test
    void t_method1() {
        assert p_method1("is");
    }
}

class Test8 {
    private String method1() {
        return "This is method1";
    }
    @TestProperty
    boolean p_method1(String s) {
        return method1().startsWith(s);
    }
}