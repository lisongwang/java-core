package com.lisong.learn.core.containers.util;

import com.lisong.learn.core.util.RandomGenerator;

import java.util.Collection;

import static com.lisong.learn.core.util.Print.print;

public class MyArrayList {
    private int capacity = 16;
    private int size = 0;
    public MyArrayList() {
        items = new String[capacity];
    }
    public MyArrayList(int initSize) {
        capacity = initSize;
        items = new String[capacity];
    }
    private String[] items;

    public String get(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return items[index];
    }

    public void add(String value) {
        ensureCapacity(size + 1);
        items[size++] = value;
    }

    public void add(int index, String value) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        ensureCapacity(size + 1);
        System.arraycopy(items, index, items, index+1, size-index);
        items[index] = value;
        size++;
    }

    public String set(int index, String value) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        String oldValue = items[index];
        items[index] = value;
        return oldValue;
    }

    public void addAll(Collection<String> data) {
        String[] copy = data.toArray(new String[0]);
        ensureCapacity(size + copy.length);
        System.arraycopy(copy,0, items, size, copy.length);
        size += copy.length;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for(int i = 0; i < size; i++)
            items[i] = null;
        size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        if(items.length < minCapacity) {
            int newCapacity = items.length + items.length>>1;
            if(newCapacity < minCapacity)
                newCapacity = minCapacity;
            String[] newItems = new String[newCapacity];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
    }

    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        list.addAll(new CollectionData<>(new RandomGenerator.String(), 1000));
        list.clear();
        list.add("a");
        list.add("b");
        list.add("d");
        list.add("e");
        list.add(2, "c");
        list.set(1, "g");
        for(int i = 0; i < list.size(); i++)
            print(list.get(i));
    }
}