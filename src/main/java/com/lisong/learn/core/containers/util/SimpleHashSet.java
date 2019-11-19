package com.lisong.learn.core.containers.util;

import java.util.*;

public class SimpleHashSet<T> extends AbstractSet<T> {

    private SimpleHashMap3<T,Object> map = new SimpleHashMap3<>();
    private Object emptyObj = new Object();

    @Override
    public boolean add(T t) { return map.put(t,emptyObj) == null; }

    @Override
    public Iterator<T> iterator() { return map.keySet().iterator(); }

    @Override
    public int size() { return map.size(); }

    @Override
    public boolean remove(Object o) {
        return map.remove(o)==emptyObj;
    }

    @Override
    public void clear() { map.clear(); }

    public static void main(String[] args) {
        Set<String> set = new SimpleHashSet();
        set.add("a");
        set.add("b");
        Iterator<String> it = set.iterator();
        set.remove("a");
        while(it.hasNext())
            it.next();
    }
}