package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Exercise12 implements Runnable {
    private AtomicInteger i = new AtomicInteger(0);
    private int getValue() { return i.get(); }
    private void evenIncrement() { i.addAndGet(2); }
    @Override
    public void run() {
        while(true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService exs = Executors.newScheduledThreadPool(1);
        exs.schedule(()->{
            System.out.println("Aborting");
            System.exit(0);
                }, 5, TimeUnit.SECONDS);
        exs.shutdown();
        ExecutorService es = Executors.newCachedThreadPool();
        Exercise12 exe12 = new Exercise12();
        es.execute(exe12);
        es.shutdown();
        while(true) {
            int val = exe12.getValue();
            if(val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}