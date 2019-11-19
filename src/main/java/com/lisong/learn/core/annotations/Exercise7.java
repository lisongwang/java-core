package com.lisong.learn.core.annotations;

import com.lisong.learn.core.annotations.atunit.Test;

import java.util.LinkedList;

public class Exercise7 extends LinkedList<String> {

    @Test
    private void i_initialization() {
        assert isEmpty();
    }

    @Test
    private void i_contains() {
        add("one");
        assert contains("one");
    }

    @Test
    private void i_remove() {
        add("one");
        remove("one");
        assert isEmpty();
    }

    @Test
    private void i_add() {
        add("one");
        assert size() == 2 : "size incorrect";
    }
}