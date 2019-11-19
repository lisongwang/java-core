package com.lisong.learn.core.generics;

import com.lisong.learn.core.type.pet.*;
import com.lisong.learn.core.util.Generator;

import java.lang.reflect.Constructor;
import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise41 {

    public static void main(String[] args) {

        List<Pet> petList = new ArrayList<>();
        Fill2.fill(AdptorHelper.getCollectionAdptor(petList), Cymric.class, 3);
        Fill2.fill(AdptorHelper.getCollectionAdptor(petList), Mutt.class, 2);
        Fill2.fill(AdptorHelper.getCollectionAdptor(petList), Gerbil.class, 1);
        print(petList);

        SimpleQueueAdptor<Rodent> queueAdptor = new SimpleQueueAdptor<>();
        Fill2.fill(queueAdptor, Rat.class, 2);
        Fill2.fill(queueAdptor, Mouse.class, 3);
        print(queueAdptor);
    }
}

interface Addable<T> {
    void add(T t);
}

class Fill2 {
    static <T> void fill(Addable<T> seq, Class<? extends T> clazz, int size) {
        for(int i = 0; i < size; i++) {
            try {
                Constructor<? extends T> c = clazz.getDeclaredConstructor();
                c.setAccessible(true);
                seq.add(c.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static <T> void fill(Addable<T> seq, Generator<T> gen, int size) {
        for(int i = 0; i < size; i++)
            seq.add(gen.next());
    }
}

class CollectionAdptor<T> implements Addable<T> {
    private Collection<T> collection;
    CollectionAdptor(Collection<T> c) { collection = c; }
    @Override
    public void add(T t) {
        collection.add(t);
    }
}

class AdptorHelper {
    static <T> Addable<T> getCollectionAdptor(Collection<T> collection) {
        return new CollectionAdptor<>(collection);
    }
}

class SimpleQueue<T> implements Iterable<T> {
    private Queue<T> queue = new LinkedList<>();
    void add(T t) {
        queue.offer(t);
    }
    T get() { return queue.poll(); }

    @Override
    public Iterator<T> iterator() {
        return queue.iterator();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

class SimpleQueueAdptor<T> extends SimpleQueue<T> implements Addable<T> {
    @Override
    public void add(T t) {
        super.add(t);
    }
}