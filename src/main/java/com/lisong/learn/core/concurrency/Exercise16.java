package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.lisong.learn.core.util.Print.print;

public class Exercise16 {
    private final int size = 10;
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    private Lock lock3 = new ReentrantLock();
    private void f() {
        lock1.lock();
        try {
            for(int i = 0; i < size; i++) {
                print("f()");
                Thread.yield();
            }
        }finally {
            lock1.unlock();
        }
    }
    private void g() {
        lock2.lock();
        try {
            for(int i = 0; i < size; i++) {
                print("g()");
                Thread.yield();
            }
        }finally {
            lock2.unlock();
        }
    }
    private void h() {
        lock3.lock();
        try {
            for(int i = 0; i < size; i++) {
                print("h()");
                Thread.yield();
            }
        }finally {
            lock3.unlock();
        }
    }
    public static void main(String[] args) {
        Exercise16 exe16 = new Exercise16();
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(exe16::f);
        es.execute(exe16::g);
        es.execute(exe16::h);
        es.shutdown();
    }
}