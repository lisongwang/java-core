package com.lisong.learn.core.containers.util;

import com.lisong.learn.core.util.CountingGenerator;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class MySortedSet<T> extends AbstractSet<T> implements SortedSet<T> {

    private LinkedList<T> list = new LinkedList<>();

    private final Comparator<T> comparator;

    public MySortedSet(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    public MySortedSet() {
        comparator = null;
    }

    private MySortedSet(LinkedList<T> list) {
        this.list = list;
        comparator = null;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public boolean add(T t) {
        if(contains(t))
            return false;
        if(comparator != null)
            return addWithComparator(t);

        Comparable c1 = (Comparable)t;
        ListIterator<T> it = list.listIterator();
        while(it.hasNext()) {
            Comparable c2 = (Comparable)it.next();
            if(c2.compareTo(c1) == 0)
                return false;
            if(c2.compareTo(c1) > 0) {
                it.previous();
                it.add(t);
                return true;
            }
        }
        it.add(t);
        return true;
    }

    private boolean addWithComparator(T t) {
        ListIterator<T> it = list.listIterator();
        while(it.hasNext()) {
            T t2 = it.next();
            if(comparator.compare(t2, t) == 0)
                return false;
            if(comparator.compare(t2, t) > 0) {
                it.previous();
                it.add(t);
                return true;
            }
        }
        it.add(t);
        return true;
    }

    @Override
    public boolean remove(Object o) { return list.remove(o); }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() { return list.size(); }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T first() {
        return list.getFirst();
    }

    @Override
    public T last() {
        return list.getLast();
    }

    public static void main(String[] args) {
        SortedSet<String> set = new MySortedSet<>(String.CASE_INSENSITIVE_ORDER);
        set.addAll(new CollectionData<>(new CountingGenerator.String(), 20));
        print(set);
        Set<String> subset = set.subSet("GHIJKLM", "QRSTUVW");
        print(subset);
        subset.clear();
        print(set);
    }
}