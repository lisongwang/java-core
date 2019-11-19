package com.lisong.learn.core.util;

import java.lang.reflect.Array;
import java.util.*;

public class Generators {

    public static <T> Collection<T> fill(Collection<T> collection, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            collection.add(gen.next());
        return collection;
    }

    public static <T> List<T> fill(List<T> list, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            list.add(gen.next());
        return list;
    }

    public static <T> LinkedList<T> fill(LinkedList<T> list, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            list.add(gen.next());
        return list;
    }

    public static <T> Set<T> fill(Set<T> set, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            set.add(gen.next());
        return set;
    }

    public static <T> Queue<T> fill(Queue<T> queue, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            queue.add(gen.next());
        return queue;
    }

    public static <T> T[] fillArray(T[] tArray, Generator<T> gen) {
        for(int i = 0; i < tArray.length; i++)
            tArray[i] = gen.next();
        return tArray;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] fillArray(Class<T> type, Generator<T> gen, int length) {
        return fillArray((T[]) Array.newInstance(type, length), gen);
    }
}
