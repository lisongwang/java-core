package com.lisong.learn.core.operators;

import static com.lisong.learn.core.util.Print.print;

/**
 * Show the bitwise operator on binary digital.
 */
public class Exercise10 {

    public static void printBInt(int i1, int i2) {

        print("i1: " + Integer.toBinaryString(i1));
        print("i2: " + Integer.toBinaryString(i2));

        print("i1 & i2 = " + Integer.toBinaryString(i1 & i2));
        print("i1 | i2 = " + Integer.toBinaryString(i1 | i2));
        print("i1 ^ i2 = " + Integer.toBinaryString(i1 ^ i2));
        print("~i1 = " + Integer.toBinaryString(~i1));
        print("~i2 = " + Integer.toBinaryString(~i2));
    }

    public static void main(String[] args) {

        int i1 = 1 + 4 + 16 + 64;
        int i2 = 2 + 8 + 32 + 128;

        printBInt(i1, i2);

        boolean b1 = true;
        boolean b2 = false;

        print("b1 & b2 = " + (b1&b2));
        print("b1 | b2 = " + (b1|b2));
        print("b1 ^ b2 = " + (b1^b2));
        print("!b1 = " + (!b1));
    }
}
