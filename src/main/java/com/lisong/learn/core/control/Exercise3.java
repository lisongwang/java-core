package com.lisong.learn.core.control;

import java.util.Random;

/**
 * Combine exercise 1, exercise 2, exercise 3.
 */
public class Exercise3 {

    static void printInt() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {

        printInt();

        Random rand1 = new Random();
        Random rand2 = new Random();

        for (int i = 0; i < 25; i++) {

            int l = rand1.nextInt();
            int m = rand2.nextInt();
            if (l > m)
                System.out.println(l + " > " + m);
            else if (l < m)
                System.out.println(l + " < " + m);
            else if (l == m)
                System.out.println(l + " = " + m);
        }
/*
        Random rand3 = new Random();
        Random rand4 = new Random();

        while (true) {

            int t = rand3.nextInt();
            int e = rand4.nextInt();
            if (t > e)
                System.out.println(t + " > " + e);
            else if (t < e)
                System.out.println(t + " < " + e);
            else if (t == e)
                System.out.println(t + " = " + e);
        }

 */
    }
}
