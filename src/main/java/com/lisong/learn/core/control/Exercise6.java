package com.lisong.learn.core.control;

import static com.lisong.learn.core.util.Range.range;

/**
 * Show return statement.
 */
public class Exercise6 {

    static int test(int testVal, int begin, int end) {

        if (testVal < begin)
            return -1;
        else if (testVal > end)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) {

        for (int i : range(10)) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i : range(5, 10)) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i : range(5, 20, 3)) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println(test(6, 2, 8));
        System.out.println(test(1, 2, 8));
        System.out.println(test(9, 2, 8));
    }
}
