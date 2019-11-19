package com.lisong.learn.core.annotations;

import com.lisong.learn.core.annotations.atunit.Test;
import com.lisong.learn.core.containers.util.SimpleHashMap2;

public class Exercise9 extends SimpleHashMap2<Integer,String> {

    @Test
    void im_initialization() {
        assert isEmpty();
    }
    @Test
    void im_put() {
        put(1, "One");
        put(1, "Two");
        assert size() == 1;
    }
    @Test
    void im_get() {
        put(1, "One");
        put(1, "Two");
        assert get(1).equals("Two");
    }
    @Test
    void im_putIfAbsent() {
        putIfAbsent(1, "One");
        putIfAbsent(1, "Two");
        assert get(1).equals("One");
    }
    @Test
    void im_containsKey() {
        put(1, "One");
        assert containsKey(1);
    }
    @Test
    void im_containsValue() {
        put(1, "One");
        assert containsValue("One");
    }
    @Test
    void im_remove() {
        put(1, "One");
        remove(1);
        assert size() == 0;
    }
    @Test
    void im_replace() {
        put(1, "One");
        replace(1, "Two");
        assert get(1).equals("Two");
    }
    @Test
    void im_compute() {
        put(1, "One");
        computeIfPresent(1, (k,v)->v+k);
        assert get(1).equals("One1");
    }
    @Test
    void im_clear() {
        put(1, "One");
        put(2, "Two");
        clear();
        assert isEmpty();
    }
}