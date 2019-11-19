package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static com.lisong.learn.core.util.Print.print;

public class Exercise9 implements Runnable {
    private int countDown = 5;
    private volatile long d;

    @Override
    public String toString() {
        return Thread.currentThread() + " " + countDown;
    }

    @Override
    public void run() {
        while(true) {
            for(int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E)/(double)i;
                if(i%10 == 0) {
                    Thread.yield();
                }
            }
            print(this);
            if(countDown-- == 0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService esm = Executors.newCachedThreadPool(new PriorityThreadFactory(Thread.MIN_PRIORITY));
        ExecutorService esx = Executors.newCachedThreadPool(new PriorityThreadFactory(Thread.MAX_PRIORITY));
        for(int i = 0; i < 5; i++)
            esm.execute(new Exercise9());
        esx.execute(new Exercise9());
        esm.shutdown();
        esx.shutdown();
    }
}

class PriorityThreadFactory implements ThreadFactory {
    private int priority;
    PriorityThreadFactory(int priority) {
        this.priority = priority;
    }
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setPriority(priority);
        return t;
    }
}