package com.lisong.learn.core.holding;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.lisong.learn.core.util.Print.print;

public class Exercise30 {

    static void display(Iterator it) {
        while(it.hasNext())
            print(it.next());
    }

    static void display(Collection c) {
        for(Object o : c)
            print(o);
    }

    public static void main(String[] args) {

        CollectionSequence gerbils = new CollectionSequence();
        for(int i = 0; i < 30; i++)
            gerbils.add(new Gerbil(i));
        print(gerbils.size());
        gerbils.clear();
        for(int i = 0; i < 10; i++)
            gerbils.add(new Gerbil(i));
        print(gerbils.size());
        //display(gerbils.iterator());
        display(gerbils);
        print(gerbils.contains(new Gerbil(2)));
    }
}

class CollectionSequence implements Collection {

    private static final int INIT_CAPACITY = 16;

    private int capacity = INIT_CAPACITY;

    private int next = -1;

    private Gerbil[] gerbils = new Gerbil[capacity];

    CollectionSequence() {}

    CollectionSequence(int initCapacity) {
        capacity = initCapacity;
        gerbils = new Gerbil[capacity];
    }

    CollectionSequence(Collection<CollectionSequence> source) {
        addAll(Objects.requireNonNull(source));
    }

    @Override
    public boolean removeIf(Predicate filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Spliterator spliterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream stream() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream parallelStream() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        if (next >= Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return next + 1;
    }

    @Override
    public boolean isEmpty() {
        return next == -1;
    }

    @Override
    public boolean contains(Object o) {
        for(Gerbil g : gerbils) {
            if(Objects.equals(g, o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {

            private int nextIndex = 0;
            @Override
            public boolean hasNext() {
                return nextIndex <= next;
            }

            @Override
            public Object next() {
                return gerbils[nextIndex++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Object o) {
        if (next == gerbils.length - 1) {
            capacity <<= 1;
            Gerbil[] newGerbils = new Gerbil[capacity];
            for(int i = 0; i < gerbils.length; i++) {
                newGerbils[i] = gerbils[i];
            }
            gerbils = newGerbils;
        }
        gerbils[++next] = (Gerbil)o;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection c) {
        for(Object o : Objects.requireNonNull(c)) {
            if(!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        for(Object o : Objects.requireNonNull(c)) {
            add(o);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        capacity = INIT_CAPACITY;
        gerbils = new Gerbil[capacity];
        next = -1;
    }
}
