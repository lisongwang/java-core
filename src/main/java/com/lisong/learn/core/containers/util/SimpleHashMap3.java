package com.lisong.learn.core.containers.util;

import java.util.ArrayList;
import java.util.List;

public class SimpleHashMap3<K,V> extends SimpleHashMap2<K,V> {
    @Override
    protected List<Entry<K, V>> emptyList() { return new ArrayList<>(); }

    public static void main(String[] args) {
        Maps.testMap(new SimpleHashMap3<>());
        Maps.testMapAPI(new SimpleHashMap3<>());
    }
}