package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.Maps;
import com.lisong.learn.core.containers.util.SlowMap;

/**
 * Combine exercise 16, exercise 17.
 */
public class Exercise17 {

    public static void main(String[] args) {
        Maps.testMap(new SlowMap<>());
        Maps.testMapAPI(new SlowMap<>());
    }
}