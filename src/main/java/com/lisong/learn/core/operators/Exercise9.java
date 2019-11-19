package com.lisong.learn.core.operators;

import static com.lisong.learn.core.util.Print.print;

/**
 * Show the max and min value of float and double.
 */
public class Exercise9 {

    public static void main(String[] args) {

        float f_max = Float.MAX_VALUE;
        float f_min = Float.MIN_VALUE;

        print("Min value of float is: " + f_min);
        print("Max value of float is: " + f_max);

        double d_max = Double.MAX_VALUE;
        double d_min = Double.MIN_VALUE;

        print("Min value of double is: " + d_min);
        print("Max value of double is: " + d_max);
    }
}
