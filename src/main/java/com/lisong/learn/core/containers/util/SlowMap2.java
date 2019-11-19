package com.lisong.learn.core.containers.util;

import java.util.*;

public class SlowMap2<K,V> extends AbstractMap<K,V> {

    private List<Entry<K,V>> entrylist = new ArrayList<>();

    @Override
    public V put(K key, V value) {
        Entry<K,V> correctEntry = null;
        Iterator<Entry<K,V>> i = entrySet().iterator();
        if (key==null) {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (e.getKey()==null) {
                    correctEntry = e;
                    break;
                }
            }
        } else {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (key.equals(e.getKey())) {
                    correctEntry = e;
                    break;
                }
            }
        }

        V oldValue = null;
        if(correctEntry != null) {
            oldValue = correctEntry.getValue();
            correctEntry.setValue(value);
        }else {
            entrylist.add(new MyEntry<>(key,value));
        }
        return oldValue;
    }

    @Override
    public Set<Entry<K,V>> entrySet() {
        return new AbstractSet<Entry<K,V>>() {
            @Override
            public Iterator<Entry<K,V>> iterator() {
                return entrylist.iterator();
            }
            @Override
            public int size() {
                return entrylist.size();
            }
        };
    }

    List<Entry<K,V>> getEntrylist() { return entrylist; }

    Entry getSearchEntry(K key) { return new MyEntry<>(key,null); }

    public static void main(String[] args) {
        Maps.testMap(new SlowMap2<>());
        Maps.testMapAPI(new SlowMap2<>());
        Map<String,Integer> map = new SlowMap2<>();
        map.put("a",1);
        map.put("b",2);
        Iterator<Entry<String,Integer>> it = map.entrySet().iterator();
        map.remove("b");
        while(it.hasNext())
            it.next();
    }
}