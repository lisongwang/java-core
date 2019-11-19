package com.lisong.learn.core.containers.util;

import java.util.*;

public class SimpleHashMap2<K,V> extends AbstractMap<K,V> {
    private static final int INIT_CAPACITY = 16;
    private int capacity = INIT_CAPACITY;
    private int size = 0;
    @SuppressWarnings("unchecked")
    private List<Entry<K,V>>[] buckets = new List[capacity];
    private int modifyCount = 0;
    protected List<Entry<K,V>> emptyList() { return new LinkedList<>(); }

    @Override
    public V get(Object key) {
        int i = (capacity - 1) & hash(key);
        if(buckets[i] == null)
            return null;
        for(Entry<K,V> entry : buckets[i]) {
            if(Objects.equals(entry.getKey(),key))
                return entry.getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int i = (capacity - 1) & hash(key);
        if(buckets[i] == null) {
            buckets[i] = emptyList();
            size++;
            if(rehash()) {
                i = (capacity - 1) & hash(key);
                if(buckets[i] == null) {
                    buckets[i] = emptyList();
                    size++;
                }
            }
        }
        for(Entry<K,V> e : buckets[i]) {
            if(Objects.equals(e.getKey(),key))
                return e.setValue(value);
        }
        buckets[i].add(new MyEntry<>(key,value));
        modifyCount++;
        return null;
    }

    @Override
    public V remove(Object key) {
        Iterator<Map.Entry<K,V>> it = entrySet().iterator();
        Entry<K,V> entry = null;
        while (entry==null && it.hasNext()) {
            Map.Entry<K,V> e = it.next();
            if (Objects.equals(key, e.getKey()))
                entry = e;
        }
        V value = null;
        if (entry !=null) {
            value = entry.getValue();
            it.remove();
            modifyCount++;
        }
        return value;
    }

    @Override
    public void clear() {
        modifyCount++;
        super.clear();
    }

    @Override
    public Set<Entry<K,V>> entrySet() {
        return new EntrySet();
    }

    private int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode())^(h>>>16);
    }

    @SuppressWarnings("unchecked")
    private boolean rehash() {
        if(((float)size / (float)capacity) < 0.75f)
            return false;
        int newCapacity = (capacity << 1) + 1;
        while(!isPrime(newCapacity))
            newCapacity += 2;
        synchronized (this) {
            Set<Entry<K,V>> entrySet = new HashSet<>(entrySet());
            buckets = new List[newCapacity];
            capacity = newCapacity;
            for(Entry<K,V> entry : entrySet)
                put(entry.getKey(),entry.getValue());
        }
        return true;
    }

    private boolean isPrime(int num) {
        for (int j = 3; j <= Math.sqrt(num); j+=2) {
            if (num%j == 0)
                return false;
        }
        return true;
    }

    private class EntrySet extends AbstractSet<Entry<K,V>> {

        @Override
        public boolean retainAll(Collection<?> c) {
            if (super.retainAll(c)) {
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
            if (super.remove(o)) {
                modifyCount++;
                return true;
            } else
                return false;
        }

        @Override
        public boolean add(Map.Entry<K, V> kvEntry) {
            K key = kvEntry.getKey();
            int i = (capacity - 1) & hash(key);
            if(buckets[i] == null) {
                buckets[i] = emptyList();
                if(rehash()) {
                    i = (capacity - 1) & hash(key);
                    if(buckets[i] == null) {
                        buckets[i] = emptyList();
                        size++;
                    }
                }
                size++;
            }
            for(Entry<K,V> e : buckets[i]) {
                if(Objects.equals(e.getKey(),key))
                    return false;
            }
            buckets[i].add(kvEntry);
            modifyCount++;
            return true;
        }

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new Iterator<Map.Entry<K, V>>() {
                private int i, j = 0;
                private int last_i,last_j = -1;
                private int modifyInIterator = modifyCount;
                @Override
                public boolean hasNext() {
                    checkForModification();
                    //find the first element
                    if(i >= buckets.length)
                        return false;
                    if(buckets[i] == null) {
                        do {
                            if(++i >= buckets.length)
                                return false;
                        }while(buckets[i] == null);
                    }
                    return j < buckets[i].size();
                }

                @Override
                public Entry<K, V> next() {
                    checkForModification();
                    if(!hasNext())
                        throw new NoSuchElementException();

                    last_i = i;
                    last_j = j;
                    if(++j >= buckets[i].size()) {
                        //find the next list in buckets.
                        do {
                            if(++i >= buckets.length)
                                break;
                        }while(buckets[i] == null);
                        j = 0;
                    }
                    return buckets[last_i].get(last_j);
                }

                @Override
                public void remove() {
                    if(last_i == -1 || last_j == -1)
                        throw new IllegalStateException();
                    checkForModification();
                    try {
                        buckets[last_i].remove(last_j);
                        i = last_i;
                        j = last_j;
                        if(buckets[i].isEmpty()) {
                            //remove the empty list
                            buckets[i] = null;
                            size--;
                            boolean notFound = false;
                            do {
                                if(--i < 0) {
                                    notFound = true;
                                    break;
                                }
                            }while(buckets[i] == null);
                            if(notFound) {
                                i = 0;
                                j = 0;
                            }else
                                j = buckets[i].size() - 1;
                        }
                        last_i = -1;
                        last_j = -1;
                        modifyInIterator = modifyCount;
                    }catch(IndexOutOfBoundsException e) {
                        throw new ConcurrentModificationException();
                    }
                }

                private void checkForModification() {
                    if (modifyCount != modifyInIterator)
                        throw new ConcurrentModificationException();
                }
            };
        }

        @Override
        public int size() {
            int count = 0;
            for(Entry<K,V> entry : this) {
                count++;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        Maps.testMap(new SimpleHashMap2<>());
        Maps.testMapAPI(new SimpleHashMap2<>());
        Map<String,Integer> map = new SimpleHashMap2<>();
        map.put("a",1);
        map.put("b",2);
        Iterator<Entry<String,Integer>> it = map.entrySet().iterator();
        map.remove("b");
        while(it.hasNext())
            it.next();
    }
}