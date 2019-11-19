package com.lisong.learn.core.holding;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import static com.lisong.learn.core.util.Print.print;

public class Exercise12 {

    public static void main(String[] args) {

        Random rand = new Random(60);
        List<Integer> ints = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ints.add(rand.nextInt(100));
        }

        List<Integer> ints2 = new ArrayList<>(ints);
        ListIterator<Integer> it1 = ints.listIterator();
        ListIterator<Integer> it2 = ints2.listIterator(ints2.size());
        while(it2.hasPrevious()) {
            it2.previous();
            it2.set(it1.next());
        }

        print(ints);
        print(ints2);
    }
}
