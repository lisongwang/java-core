package com.lisong.learn.core.holding;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 13, exercise 14.
 */
public class Exercise14 {

    static void addMiddle(List<Integer> ints, Integer i) {

        ListIterator<Integer> it = ints.listIterator(ints.size()/2);
        it.add(i);
    }

    public static void main(String[] args) {

        List<Integer> ints = new LinkedList<>();
        addMiddle(ints, 11);
        addMiddle(ints, 20);
        addMiddle(ints, 35);
        addMiddle(ints, 46);
        addMiddle(ints, 53);
        addMiddle(ints, 33);
        addMiddle(ints, 9);
        print(ints);
    }
}
