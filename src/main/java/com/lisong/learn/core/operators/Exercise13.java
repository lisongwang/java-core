package com.lisong.learn.core.operators;

import static com.lisong.learn.core.util.Print.*;

/**
 * Combine exercise 11, exercise 12, exercise 13.
 */
public class Exercise13 {

    public static void main(String[] args) {

        int num1 = 0X10000000;
        printBinaryInt("before shift", num1);

        int i = 0;
        while(i++ < 28) {
            printBinaryInt(">>" + i, (num1 >> i));
        }

        int num2 = 0XFFFFFFFF;
        printBinaryInt("before shift", num2);
        printBinaryInt("left shift by 10" , (num2 <<= 10));

        int j = 0;
        while(j++ < 31) {
            printBinaryInt(">>>" + j, (num2 >>> j));
        }

        char num3 = 'A';
        int k = 0;
        while (k++ < 26) {
            printBinaryChar("Display char:", num3++);
        }
    }
}
