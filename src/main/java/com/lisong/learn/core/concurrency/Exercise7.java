package com.lisong.learn.core.concurrency;

import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise7 implements Runnable {
    private Thread[] threads = new Thread[10];
    @Override
    public void run() {
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                while(true) {
                    print("inside spawn thread: " + Thread.currentThread());
                    Thread.yield();
                } }, "thread: " + i);
            threads[i].setPriority(Thread.MIN_PRIORITY);
            threads[i].start();
            print("DaemonSpawn " + i + " started.");
        }
        for(Thread t : threads)
            print("Thread: " + t + " is daemon? " + t.isDaemon());
        while(true)
            Thread.yield();
    }

    public static void main(String[] args) {
        Thread t = new Thread(new Exercise7());
        t.setDaemon(true);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
        print("t.isDaemon: " + t.isDaemon());
        try {
            TimeUnit.MILLISECONDS.sleep(56);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("exit main");
    }
}