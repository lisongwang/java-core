package com.lisong.learn.core.operators;

import static com.lisong.learn.core.util.Print.print;

/**
 * Show how the hex and octal work.
 */
public class Exercise8 {

    public static void main(String[] args) {

        long l1 = -0XE213FECDL;
        print("l1: " + Long.toBinaryString(l1));
        long l2 = 0757772222222L;
        print("l2: " + Long.toBinaryString(l2));
    }
}
