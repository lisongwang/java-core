package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.Maps;
import com.lisong.learn.core.containers.util.SimpleHashMap;

/**
 * Combine exercise 22, exercise 23.
 */
public class Exercise23 {

    public static void main(String[] args) {
        Maps.testMap(new SimpleHashMap<>());
        Maps.testMapAPI(new SimpleHashMap<>());
    }
}