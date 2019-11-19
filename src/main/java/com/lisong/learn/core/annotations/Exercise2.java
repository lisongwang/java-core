package com.lisong.learn.core.annotations;

import com.lisong.learn.core.annotations.apt.InterfaceExtractor;

@InterfaceExtractor("IExercise2")
public class Exercise2 {
    private static int count = 0;
    private static final int id;
    static {
        id = count++;
    }

    public int multiple(int x, int y) {
        int total = 0;
        for(int i = 0; i < x; i++)
            total = add(total, y);
        return total;
    }
    public int division(int x, int y) { return x / y; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " id";
    }

    private int add(int x, int y) {
        return x + y;
    }

    private int result = 1;
    {
        result++;
    }

    public static void main(String[] args) {}
}