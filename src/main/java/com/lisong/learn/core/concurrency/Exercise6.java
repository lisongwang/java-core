package com.lisong.learn.core.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise6 implements Runnable {
    private static int count = 0;
    private final int id = count++;
    private Random rand;

    public Exercise6(int seed) {
        rand = new Random(seed);
    }

    @Override
    public void run() {
        int seconds = 1 + rand.nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("Task: " + id + " sleep for " + seconds + "(seconds)");
    }

    public static void main(String[] args) {
        if(args.length != 1) {
            print("Please provide the quantity");
            System.exit(0);
        }
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0; i < Integer.parseInt(args[0]); i++)
            es.execute(new Exercise6(i + 5000));
        es.shutdown();
    }
}