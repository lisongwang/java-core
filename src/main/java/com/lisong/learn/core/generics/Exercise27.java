package com.lisong.learn.core.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 26, exercise 27.
 */
public class Exercise27 {

    public static void main(String[] args) {

        Number[] narray = new Integer[]{1, 2, 3, 4, 5};
        print(Arrays.toString(narray));
        narray[0] = 'a' + 5;
        print(Arrays.toString(narray));
        //narray[1] = 10.5;

        //List<Number> nlist = new ArrayList<Integer>();
        List<? extends Number> nlist = new ArrayList<Integer>();
        List<Integer> ilist = new ArrayList<>();
        //nlist.add(10);
        nlist.add(null);
        ilist.add(10);
        print(nlist.getClass() == ilist.getClass());
    }
}
