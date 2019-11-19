package com.lisong.learn.core.generics;

public class Exercise32 {

    public static void main(String[] args) {

        int size = 10;
        FixedSizeStack<String> sstack = new FixedSizeStack<>(size);
        for(String s : "a b c d e f g h i j k l m".split(" ")) {
            if(sstack.getIndex() < size)
                sstack.push(s);
        }
        int count = sstack.getIndex();
        for(int j = 0; j < count; j++)
            System.out.print(sstack.pop() + " ");
    }
}

class FixedSizeStack<T> {

    private Object[] items;
    private int index = 0;
    FixedSizeStack(int size) {
        items = new Object[size];
    }
    void push(T t) {
        items[index++] = t;
    }
    int getIndex() { return index; }
    @SuppressWarnings("unchecked")
    T pop() {
        return (T)items[--index];
    }
}


