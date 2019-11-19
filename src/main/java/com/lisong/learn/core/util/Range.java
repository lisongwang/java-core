package com.lisong.learn.core.util;

public class Range {

    public static int[] range(int top) {

        if (top <= 0)
            return null;
        int[] numArray = new int[top];
        for (int i = 0; i < top; i++) {
            numArray[i] = i;
        }
        return numArray;
    }

    public static int[] range(int start, int top) {

        int size = top - start;
        if (size <= 0)
            return null;
        int[] numArray = new int[size];
        for (int i = 0; i < size; i++) {
            numArray[i] = start + i;
        }
        return numArray;
    }

    public static int[] range(int start, int top, int step) {

        if (step <= 0)
            return null;
        int size = (top - start)/step;
        if (size <= 0)
            return null;
        int[] numArray = new int[size];
        for (int i = 0; i < size; i++) {
            numArray[i] = start + i * step;
        }
        return numArray;
    }
}
