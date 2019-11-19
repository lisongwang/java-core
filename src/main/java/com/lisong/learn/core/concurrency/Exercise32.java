package com.lisong.learn.core.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise32 {

    public static void main(String[] args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(Integer.MAX_VALUE);
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
            es.execute(new Entrance2(cdl));
        TimeUnit.SECONDS.sleep(3);
        es.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        print("Total: " + (Integer.MAX_VALUE - cdl.getCount()));
        print("Sum of Entrance: " + Entrance2.sumEntraces());
    }
}

class Entrance2 implements Runnable {
    private static int count = 0;
    private final int id = count++;
    private int number = 0;
    private static Random rand = new Random(108);
    private static List<Entrance2> entrances = new ArrayList<>();
    private CountDownLatch countDownLatch;
    Entrance2(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        entrances.add(this);
    }
    private int getValue() { return number; }
    static int sumEntraces() {
        int sum = 0;
        for(Entrance2 e : entrances)
            sum += e.getValue();
        return sum;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                number++;
                print(this);
                countDownLatch.countDown();
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(100));
            }
        }catch (InterruptedException e) {
            print("Entrance interrupted " + this);
        }
        print("Stop " + this);
    }

    @Override
    public String toString() {
        return "Entrance " + id + ": " + number;
    }
}