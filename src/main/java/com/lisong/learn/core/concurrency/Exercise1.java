package com.lisong.learn.core.concurrency;

import static com.lisong.learn.core.util.Print.print;

public class Exercise1 implements Runnable {
    private static int count = 0;
    private final int id = count++;

    public Exercise1() {
        print("Task " + id + " start");
    }
    @Override
    public void run() {
        for(int i = 0; i < 3; i++) {
            print("Task " + id + " run in " + i + " round");
            Thread.yield();
        }
        print("Task " + id + " stop");
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++)
            new Thread(new Exercise1()).start();
    }
}