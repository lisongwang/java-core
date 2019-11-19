package com.lisong.learn.core.containers.util;

import com.lisong.learn.core.util.ConvertPrimitive;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.Collection;

import static com.lisong.learn.core.util.Print.print;

public class MyArrayList2 {
    private int capacity = 16;
    private int size = 0;
    public MyArrayList2() {
        items = new int[capacity];
    }
    public MyArrayList2(int initSize) {
        capacity = initSize;
        items = new int[capacity];
    }
    private int[] items;

    public int get(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return items[index];
    }

    public int set(int index, int value) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        int oldValue = items[index];
        items[index] = value;
        return oldValue;
    }

    public void add(int value) {
        ensureCapacity(size + 1);
        items[size++] = value;
    }

    public void add(int index, int value) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        ensureCapacity(size + 1);
        System.arraycopy(items, index, items, index+1, size-index);
        items[index] = value;
        size++;
    }

    public void addAll(Collection<Integer> data) {
        int[] copy = ConvertPrimitive.primitive(new int[0], data.toArray(new Integer[0]));
        ensureCapacity(size + copy.length);
        System.arraycopy(copy,0, items, size, copy.length);
        size += copy.length;
    }

    public void increment(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        items[index] = items[index]+1;
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        if(items.length < minCapacity) {
            int newCapacity = items.length + items.length>>1;
            if(newCapacity < minCapacity)
                newCapacity = minCapacity;
            int[] newItems = new int[newCapacity];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
    }

    public static void main(String[] args) {
        MyArrayList2 list = new MyArrayList2();
        list.addAll(new CollectionData<>(new RandomGenerator.Integer(), 1000));
        list.clear();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(2, 3);
        list.set(1, 8);
        for(int i = 0; i < list.size(); i++) {
            list.increment(i);
            print(list.get(i));
        }
    }
}