package com.lisong.learn.core.operators;

import java.util.Random;

import static com.lisong.learn.core.util.Print.print;

/**
 * Simulate coin-flipping.
 */
public class Exercise7 {

    public static void main(String[] args) {

        Random rand = new Random(47);

        int num = 10;
        print("Coin flip " + num + " times!");

        while(num-- > 0) {
            int i = rand.nextInt(100);
            i %= 2;
            print("Coin flip result: " + (i == 0 ? "正面":"反面"));
        }
    }
}
