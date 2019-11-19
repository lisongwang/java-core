package com.lisong.learn.core.containers.util;

import java.util.Map;
import java.util.Objects;

public class MyEntry<K,V> implements Map.Entry<K,V> {
    private K key;
    private V value;

    MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + (key == null ? 0 : key.hashCode());
        result = 37*result + (value == null ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (hashCode() != obj.hashCode())
            return false;
        if (this.getClass() == obj.getClass()) {
            @SuppressWarnings("unchecked")
            MyEntry entry = (MyEntry) obj;
            return Objects.equals(key, entry.getKey()) && Objects.equals(value, entry.getValue());
        }
        return false;
    }
}