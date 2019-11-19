package com.lisong.learn.core.object;

/**
 * Combine exercise 3, exercise 4 and exercise 5.
 */
public class Exercise5 {

    public static void main(String[] args) {

        class ATypeName {

            /* class body goes here */
        }

        class DataOnly {

            int i;
            double d;
            boolean b;
        }

        DataOnly data = new DataOnly();
        data.i = 1;
        data.d = 0.1d;
        data.b = true;

        System.out.println("i = " + data.i);
        System.out.println("d = " + data.d);
        System.out.println("b = " + data.b);
    }
}
