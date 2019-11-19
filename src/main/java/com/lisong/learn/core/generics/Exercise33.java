package com.lisong.learn.core.generics;

import java.util.ArrayList;
import java.util.List;

public class Exercise33 {

    public static void main(String[] args) {

        UnFixedSizeStack<String> sstack = new UnFixedSizeStack<>();
        for(String s : "a b c d e f g h i j k l m".split(" ")) {
            sstack.push(s);
        }
        int count = sstack.getSize();
        for(int j = 0; j < count; j++)
            System.out.print(sstack.pop() + " ");
    }
}


class UnFixedSizeStack<T> {

    private List<T> items = new ArrayList<>();

    void push(T t) {
        items.add(t);
    }
    int getSize() { return items.size(); }
    @SuppressWarnings("unchecked")
    T pop() {
        return items.remove(items.size()-1);
    }
}