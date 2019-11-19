package com.lisong.learn.core.containers.util;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class SimpleHashMap<K,V> extends AbstractMap<K,V> {
    private static final int SIZE = 16;
    @SuppressWarnings("unchecked")
    private Node<Entry<K,V>>[] buckets = new Node[SIZE];
    private int modifyCount = 0;
    private final Node<Entry<K,V>> HEAD = new Node<>(null,null);

    @Override
    public V get(Object key) {
        int i = (SIZE - 1) & hash(key);
        if(buckets[i] == null)
            return null;
        Node<Entry<K,V>> node = buckets[i];
        do {
            if(Objects.equals(node.t.getKey(), key))
                return node.t.getValue();
        }while((node = node.next) != null);
        return null;
    }

    @Override
    public V put(K key, V value) {
        int i = (SIZE - 1) & hash(key);
        MyEntry<K,V> entry = new MyEntry<>(key,value);
        boolean collision = false;
        if(buckets[i] == null) {
            buckets[i] = new Node<>(entry,null);
            return null;
        }
        else {
            collision = true;
            //print("Hash collision for key: " + key + " at index: " + i);
        }

        int probes = 0;
        Node<Entry<K,V>> node = buckets[i];
        Node<Entry<K,V>> cNode = node;
        do {
            probes++;
            if(Objects.equals(cNode.t.getKey(), key)) {
                //print("Probes: " + probes + " for collision key: " + key + " at index: " + i + " before match");
                return cNode.t.setValue(value);
            }
            node = cNode;
        }while((cNode = cNode.next) != null);

        //not found
        //if(collision)
            //print("Probes: " + probes + " for collision key: " + key + " at index: " + i + " without match");
        node.next = new Node<>(entry,null);
        modifyCount++;
        return null;
    }

    @Override
    public V remove(Object key) {
        Iterator<Entry<K,V>> it = entrySet().iterator();
        Entry<K,V> entry = null;

        while (entry==null && it.hasNext()) {
            Entry<K,V> e = it.next();
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

    private class Node<T> {
        private T t;
        private Node<T> next;

        Node(T t, Node<T> next) {
            this.t = t;
            this.next = next;
        }
    }

    private class EntrySet extends AbstractSet<Entry<K,V>> {

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
            K key = kvEntry.getKey();
            int i = (SIZE - 1) & hash(key);
            if(buckets[i] == null)
                buckets[i] = new Node<>(kvEntry,null);
            Node<Entry<K,V>> node = buckets[i];
            Node<Entry<K,V>> cNode = node;
            do {
                if(Objects.equals(cNode.t.getKey(), key))
                    return false;
                node = cNode;
            }while((cNode = cNode.next) != null);

            node.next = new Node<>(kvEntry,null);
            modifyCount++;
            return true;
        }

        @Override
        public Iterator<Entry<K,V>> iterator() {
            return new Iterator<Entry<K, V>>() {
                private int i = -1;
                private int last_i = -1;
                Node<Entry<K,V>> current = HEAD;
                Node<Entry<K,V>> previous = null;
                private int modifyInIterator = modifyCount;
                @Override
                public boolean hasNext() {
                    checkForModification();
                    if(current.next == null) {
                        int j = i;
                        do {
                            if(++j >= buckets.length)
                                return false;
                        }while(buckets[j] == null);
                    }
                    return true;
                }

                @Override
                public Entry<K, V> next() {
                    checkForModification();
                    if(!hasNext())
                        throw new NoSuchElementException();
                    previous = current;
                    last_i = i;
                    if(current.next == null) {
                        do {
                            if(++i >= buckets.length)
                                break;
                        }while(buckets[i] == null);
                        if(i < buckets.length)
                            current = buckets[i];
                    }else
                        current = current.next;
                    return current.t;
                }

                @Override
                public void remove() {
                    checkForModification();
                    if(previous == null)
                        throw new IllegalStateException();
                    if(last_i != i)
                        buckets[i] = current.next;
                    else
                        previous.next = current.next;
                    i = last_i;
                    current = previous;
                    previous = null;
                    modifyInIterator = modifyCount;
                }

                private void checkForModification() {
                    if(modifyCount != modifyInIterator)
                        throw new ConcurrentModificationException();
                }
            };
        }

        @Override
        public int size() {
            int count = 0;
            for(Entry<K,V> entry : entrySet())
                count++;
            return count;
        }
    }

    public static void main(String[] args) {
        SimpleHashMap<Integer,Character> map = new SimpleHashMap<>();
        Set<Entry<Integer,Character>> set = map.entrySet();
        set.add(new MyEntry<>(1,'A'));
        set.add(new MyEntry<>(2,'B'));
        set.add(new MyEntry<>(3,'C'));
        set.add(new MyEntry<>(4,'D'));
        set.remove(new MyEntry<>(1,'A'));
        set.remove(new MyEntry<>(3,'E'));
        print(map);
        Map<String,Integer> map1 = new SimpleHashMap<>();
        map1.put("a",1);
        map1.put("b",2);
        Iterator<Entry<String,Integer>> it = map1.entrySet().iterator();
        map1.clear();
        while(it.hasNext())
            it.next();
    }
}