package com.lisong.learn.core.operators;

/**
 * Show how unary minus works for int, short, byte, char...
 */
public class UnaryMinus {

    static void f(String s) {

    }

    public static void main(String[] args) {

        char c_max = '\uFFFF';
        char c = 0XFFFF;
        System.out.println("c_max == c? " + (c_max == c));

        byte b_max = 0X7F;
        short s_max = 0X7FFF;
        int b = b_max + 1;
        int i = -s_max;
        int t = b * i;

        int i1 = 1;
        f("" + i1);

        System.out.println("b: " + b + " i: " + i + " t: " + t + " c: " + Integer.toBinaryString(c));
    }
}
