package com.lisong.learn.core.util;

import java.util.LinkedList;

public class Stack<T> {

    private LinkedList<T> storage = new LinkedList<>();

    public void push(T t) { storage.push(t); }
    public T pop() { return storage.pop(); }
    public T peek() { return storage.peek(); }
    public boolean isEmpty() { return storage.isEmpty(); }

    @Override
    public String toString() {
        return storage.toString();
    }
}