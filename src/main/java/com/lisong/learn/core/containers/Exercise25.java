package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.Maps;
import com.lisong.learn.core.containers.util.SimpleHashMap;

public class Exercise25 {

    public static void main(String[] args) {
        Maps.testMap(new SimpleHashMap<>());
        Maps.testMapAPI(new SimpleHashMap<>());
    }
}