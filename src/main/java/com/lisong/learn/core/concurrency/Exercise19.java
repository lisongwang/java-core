package com.lisong.learn.core.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise19 {

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 5; i++)
            new Entrance(i);
        TimeUnit.SECONDS.sleep(3);
        Entrance.interrupt();
        TimeUnit.SECONDS.sleep(1);
        print("Total: " + Entrance.getTotalCount());
        print("Sum of Entrance: " + Entrance.sumEntraces());
    }
}

class Count {
    private int count = 0;
    private Random rand = new Random(18);
    synchronized int increment() {
        int temp = count;
        if(rand.nextBoolean())
            Thread.yield();
        return (count = ++temp);
    }
    synchronized int value() { return count; }
}

class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<>();
    private int number = 0;
    private final int id;
    synchronized int getValue() { return number; }
    static int getTotalCount() { return count.value(); }
    static int sumEntraces() {
        int sum = 0;
        for(Entrance e : entrances)
            sum += e.getValue();
        return sum;
    }
    private Thread t;
    Entrance(int id) {
        this.id = id;
        t = new Thread(this);
        entrances.add(this);
        t.start();
    }

    static void interrupt() {
        for(Entrance e : entrances)
            e.t.interrupt();
    }

    @Override
    public void run() {
        while(true) {
            synchronized (this) {
                ++number;
            }
            print(this + " Total: " +  count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(70);
            } catch (InterruptedException e) {
                print("Sleep interrupted " + this);
                break;
            }
        }
        print("Stop " + this);
    }

    @Override
    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }
}