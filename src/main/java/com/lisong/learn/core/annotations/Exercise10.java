package com.lisong.learn.core.annotations;

import com.lisong.learn.core.annotations.atunit.Test;
import com.lisong.learn.core.annotations.atunit.TestNote;
import com.lisong.learn.core.containers.util.MySortedSet;

public class Exercise10 extends MySortedSet<Integer> {

    @Test
    void is_initialization() { assert isEmpty(); }
    @Test
    void is_add() {
        add(5);
        add(5);
        assert size() == 1;
    }
    @Test
    void is_contains() {
        add(8);
        assert contains(8);
    }
    @Test
    void is_first() {
        add(9);
        add(4);
        add(6);
        assert first() == 4;
    }
    @Test
    void is_last() {
        add(9);
        add(18);
        add(4);
        assert last() == 18;
    }
    @Test
    void is_remove() {
        add(7);
        add(9);
        remove(7);
        assert !contains(7);
    }
    @Test
    @TestNote("This feature will be supported in future")
    void is_subSet() {
        add(7);
        add(3);
        add(9);
        add(5);
        add(13);
        assert subSet(3, 13).size() == 4;
    }
    @Test
    @TestNote("This feature will be supported in next release")
    void is_tailSet() {
        add(6);
        add(10);
        add(2);
        add(8);
        add(4);
        assert tailSet(6).size() == 3;
    }
    @Test
    @TestNote("This feature is not supported by now")
    void is_headSet() {
        add(31);
        add(15);
        add(45);
        add(11);
        add(99);
        assert headSet(15).size() == 1;
    }
    @Test
    void is_clear() {
        add(77);
        add(99);
        add(44);
        clear();
        assert isEmpty();
    }
}