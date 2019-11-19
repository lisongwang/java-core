package com.lisong.learn.core.exceptions;

/**
 * Combine exercise 27, exercise 28.
 */
public class Exercise28 {

    static void f() {

        int[] ints = new int[2];
        try {
            ints[2] = 1;
        }catch(ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }

    static void g() throws Exception28 {
        throw new Exception28();
    }

    public static void main(String[] args) {
        f();
        g();
    }
}

class Exception28 extends RuntimeException {}