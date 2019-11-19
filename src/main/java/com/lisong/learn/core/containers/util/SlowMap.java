package com.lisong.learn.core.containers.util;

import java.util.*;

public class SlowMap<K,V> extends AbstractMap<K,V> {

    private List<K> keylist = new ArrayList<>();
    private List<V> valuelist = new ArrayList<>();
    private int modifyCount = 0;

    @Override
    public V put(K key, V value) {
        V oldValue = get(key);
        if(!containsKey(key)) {
            keylist.add(key);
            valuelist.add(value);
            modifyCount++;
        }else {
            valuelist.set(keylist.indexOf(key),value);
        }
        return oldValue;
    }

    @Override
    public V remove(Object key) {
        Iterator<Entry<K,V>> i = entrySet().iterator();
        Entry<K,V> correctEntry = null;
        if (key==null) {
            while (correctEntry==null && i.hasNext()) {
                Entry<K,V> e = i.next();
                if (e.getKey()==null)
                    correctEntry = e;
            }
        } else {
            while (correctEntry==null && i.hasNext()) {
                Entry<K,V> e = i.next();
                if (key.equals(e.getKey()))
                    correctEntry = e;
            }
        }

        V oldValue = null;
        if (correctEntry !=null) {
            oldValue = correctEntry.getValue();
            i.remove();
            modifyCount++;
        }
        return oldValue;
    }

    @Override
    public void clear() {
        super.clear();
        modifyCount++;
    }

    private Set<Map.Entry<K, V>> entrySet = new EntrySet();

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet;
    }

    private class MyEntry implements Map.Entry<K,V> {
        private int pos;

        MyEntry(int pos) {
            this.pos = pos;
        }

        @Override
        public K getKey() {
            return keylist.get(pos);
        }

        @Override
        public V getValue() {
            return valuelist.get(pos);
        }

        @Override
        public V setValue(V value) {
            V oldValue = getValue();
            valuelist.set(pos,value);
            return oldValue;
        }

        @Override
        public int hashCode() {
            return (getKey() == null ? 0 : getKey().hashCode()) ^
                    (getValue() == null ? 0 : getValue().hashCode());
        }

        @Override
        public boolean equals(Object obj) {
            if(!(this.getClass().isInstance(obj)))
                return false;
            MyEntry entry = (MyEntry)obj;
            return Objects.equals(getKey(),entry.getKey()) && Objects.equals(getValue(),entry.getValue());
        }
    }

    private class EntrySet extends AbstractSet<Map.Entry<K,V>> {
        @Override
        public boolean retainAll(Collection<?> c) {
            if(super.retainAll(c)) {
                modifyCount++;
                return true;
            } else
                return false;
        }

        @Override
        public void clear() {
            modifyCount++;
            super.clear();
        }

        @Override
        public boolean remove(Object o) {
            if(super.remove(o)) {
                modifyCount++;
                return true;
            } else
                return false;
        }

        @Override
        public boolean add(Entry<K, V> kvEntry) {
            if(!super.contains(kvEntry)) {
                keylist.add(kvEntry.getKey());
                valuelist.add(kvEntry.getValue());
                modifyCount++;
                return true;
            }
            return false;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new Iterator<Map.Entry<K, V>>() {
                private MyEntry entry = new MyEntry(0);
                private MyEntry lastEntry = new MyEntry(-1);
                private int modifyInIterator = modifyCount;
                @Override
                public boolean hasNext() {
                    return entry.pos < keylist.size();
                }

                @Override
                public Entry<K, V> next() {
                    checkForModification();
                    if(entry.pos >= keylist.size())
                        throw new ConcurrentModificationException();
                    lastEntry.pos = entry.pos;
                    entry.pos++;
                    return lastEntry;
                }

                @Override
                public void remove() {
                    if(lastEntry.pos == -1)
                        throw new IllegalStateException();
                    checkForModification();
                    try {
                        keylist.remove(lastEntry.pos);
                        valuelist.remove(lastEntry.pos);
                        entry.pos = lastEntry.pos;
                        lastEntry.pos = -1;
                        modifyInIterator = modifyCount;
                    }catch(IndexOutOfBoundsException e) {
                        throw new ConcurrentModificationException();
                    }
                }

                private void checkForModification() {
                    if(modifyCount != modifyInIterator)
                        throw new ConcurrentModificationException();
                }
            };
        }

        @Override
        public int size() {
            return keylist.size();
        }
    }

    public static void main(String[] args) {
        Map<String,Integer> map = new SlowMap<>();
        map.put("a",1);
        map.put("b",2);
        Iterator<Entry<String,Integer>> it = map.entrySet().iterator();
        map.put("c",3);
        while(it.hasNext())
            it.next();
    }
}