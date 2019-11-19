package com.lisong.learn.core.util;

public class Fibonacci implements Generator<Integer> {

    private int count = 0;

    @Override
    public Integer next() {
        return f(count++);
    }

    private int f(int n) {
        if(n < 2)
            return 1;
        return f(n-2) + f(n-1);
    }
}
