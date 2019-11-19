package com.lisong.learn.core.annotations;

import com.lisong.learn.core.annotations.atunit.Test;

import java.util.LinkedList;
import java.util.List;

public class Exercise6 {

    private List<String> testObject = new LinkedList<>();

    @Test
    private void t_initialization() {
        assert testObject.isEmpty();
    }

    @Test
    private void t_contains() {
        testObject.add("one");
        assert testObject.contains("one");
    }

    @Test
    private void t_remove() {
        testObject.add("one");
        testObject.remove("one");
        assert testObject.isEmpty();
    }

    @Test
    private void t_add() {
        testObject.add("one");
        assert testObject.size() == 2 : "size incorrect";
    }
}