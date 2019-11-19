package com.lisong.learn.core.control;

import com.lisong.learn.core.operators.Exercise10;

/**
 * Combine exercise 4, exercise 5.
 */
public class Exercise5 {

    static void printBinaryInt(int num) {

        if (num == 0)
            System.out.print(0);
        else {
            int lz = Integer.numberOfLeadingZeros(num);
            num <<= lz;

            for (int i = 0; i < 32 - lz; i++) {
                System.out.print(Integer.numberOfLeadingZeros(num) == 0 ? 1 : 0);
                num <<= 1;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int i1 = 1 + 4 + 16 + 64;
        int i2 = 2 + 8 + 32 + 128;

        System.out.println("Print in ordinary...");
        Exercise10.printBInt(i1, i2);

        System.out.println("Print in new...");
        System.out.print("i1: ");
        printBinaryInt(i1);
        System.out.print("i2: ");
        printBinaryInt(i2);
        System.out.print("i1 & i2 = ");
        printBinaryInt(i1&i2);
        System.out.print("i1 | i2 = ");
        printBinaryInt(i1|i2);
        System.out.print("i1 ^ i2 = ");
        printBinaryInt(i1^i2);
        System.out.print("~i1 = ");
        printBinaryInt(~i1);
        System.out.print("~i2 = ");
        printBinaryInt(~i2);
    }
}
