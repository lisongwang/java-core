package com.lisong.learn.core.holding;

import com.lisong.learn.core.innerclasses.facade.Sequence;

import java.util.Iterator;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 8, exercise 9.
 */
public class Exercise9 {

    public static void main(String[] args) {

        Sequence<String> seq = new Sequence<>(5);

        for(int i = 0; i < 5; i++)
            seq.addItem("Object " + i);

        Iterator<String> it = seq.iterator();
        while(it.hasNext()) {
            print(it.next());
        }
    }
}
