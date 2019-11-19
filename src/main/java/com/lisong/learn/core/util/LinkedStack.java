package com.lisong.learn.core.util;

public class LinkedStack<T> {

    private class Node {

        Node next;
        T item;
        Node() { item = null; next = null; }
        Node(T t, Node node) {
            item = t;
            next = node;
        }
        boolean end() { return item == null && next == null; }
    }

    private Node top = new Node();

    public boolean isEmpty() { return top.end(); }

    public void push(T t) { top = new Node(t, top); }

    public T pop() {
        if(top.end())
            return null;
        T result = top.item;
        top = top.next;
        return result;
    }
}
