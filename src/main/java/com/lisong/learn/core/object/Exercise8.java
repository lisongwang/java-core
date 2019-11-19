package com.lisong.learn.core.object;

/**
 * Show the differences between static field and instance field.
 */
public class Exercise8 {

    private static int i;

    private int j;

    public static void main(String[] args) {

        Exercise8 ex2 = new Exercise8();
        ex2.i++;
        ex2.j++;
        System.out.println(ex2.i);
        System.out.println(ex2.j);

        Exercise8 ex3 = new Exercise8();
        ex3.i++;
        ex3.j++;
        System.out.println(ex3.i);
        System.out.println(ex3.j);
    }
}
