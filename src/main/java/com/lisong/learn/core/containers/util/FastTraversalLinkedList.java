package com.lisong.learn.core.containers.util;

import com.lisong.learn.core.util.Generator;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class FastTraversalLinkedList<T> extends AbstractList<T> {
    private LinkedList<T> llist = new LinkedList<>();
    private ArrayList<T> alist = new ArrayList<>();

    @Override
    public T get(int index) {
        return alist.get(index);
    }

    @Override
    public int size() {
        return alist.size();
    }

    @Override
    public boolean add(T t) {
        return llist.add(t) && alist.add(t);
    }

    @Override
    public void add(int index, T element) {
        llist.add(index,element);
        alist.add(index,element);
    }

    @Override
    public T set(int index, T element) {
        llist.set(index,element);
        return alist.set(index, element);
    }

    @Override
    public T remove(int index) {
        llist.remove(index);
        return alist.remove(index);
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<T>() {
            private ListIterator<T> it = llist.listIterator(index);
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public T next() {
                return it.next();
            }

            @Override
            public boolean hasPrevious() {
                return it.hasPrevious();
            }

            @Override
            public T previous() {
                return it.previous();
            }

            @Override
            public int nextIndex() {
                return it.nextIndex();
            }

            @Override
            public int previousIndex() {
                return it.previousIndex();
            }

            @Override
            public void remove() {
                alist.remove(it.previousIndex());
                it.remove();
            }

            @Override
            public void set(T t) {
                alist.set(it.previousIndex(),t);
                it.set(t);
            }

            @Override
            public void add(T t) {
                alist.add(it.nextIndex(),t);
                it.add(t);
            }
        };
    }

    public static void main(String[] args) {
        Generator<String> gen = new RandomGenerator.String();
        FastTraversalLinkedList<String> list = new FastTraversalLinkedList<>();
        list.addAll(new CollectionData<>(gen, 10));
        ListIterator<String> it = list.listIterator(4);
        while(it.hasNext()) {
            String s = it.next();
            it.add("NEW");
        }
        list.remove(0);
        for(String s : list)
            printnb(s + " ");
        print();
        list.clear();
        print("list size(): " + list.size());
    }
}