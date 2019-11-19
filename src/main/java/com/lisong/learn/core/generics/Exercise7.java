package com.lisong.learn.core.generics;

import com.lisong.learn.core.util.Fibonacci;

import java.util.Iterator;

import static com.lisong.learn.core.util.Print.printnb;

public class Exercise7 {

    public static void main(String[] args) {

        IterableFibonacci fib = new IterableFibonacci(18);
        for(int i : fib)
            printnb(i + " ");
    }
}

class IterableFibonacci implements Iterable<Integer> {

    private Fibonacci fib = new Fibonacci();

    private int size = 0;
    IterableFibonacci(int size) { this.size = size; }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int count = size;
            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public Integer next() {
                count--;
                return fib.next();
            }
        };
    }
}
