package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.lisong.learn.core.util.Print.print;

public class Exercise15 {
    private final int size = 10;
    private final Object synObj1 = new Object();
    private final Object synObj2 = new Object();
    private final Object synObj3 = new Object();
    private void f() {
        synchronized (synObj1) {
            for(int i = 0; i < size; i++) {
                print("f()");
                Thread.yield();
            }
        }
    }
    private void g() {
        synchronized (synObj2) {
            for(int i = 0; i < size; i++) {
                print("g()");
                Thread.yield();
            }
        }
    }
    private void h() {
        synchronized (synObj3) {
            for(int i = 0; i < size; i++) {
                print("h()");
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        Exercise15 exe15 = new Exercise15();
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(exe15::f);
        es.execute(exe15::g);
        es.execute(exe15::h);
        es.shutdown();
    }
}