package com.lisong.learn.core.containers.util;

import java.util.*;

public class SlowMap3<K,V> extends SlowMap2<K,V> {

    @Override
    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        V v = super.put(key, value);
        getEntrylist().sort((e1,e2)->((Comparable)e1.getKey()).compareTo(e2.getKey()));
        return v;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        int index = Collections.binarySearch(getEntrylist(),
                getSearchEntry((K)key),(e1,e2)->((Comparable)e1.getKey()).compareTo(e2.getKey()));
        return index < 0 ? null : getEntrylist().get(index).getValue();
    }

    @Override
    public Set<Map.Entry<K,V>> entrySet() {
        return super.entrySet();
    }

    public static void main(String[] args) {
        Maps.testMap(new SlowMap3<>());
        Maps.testMapAPI(new SlowMap3<>());
    }
}