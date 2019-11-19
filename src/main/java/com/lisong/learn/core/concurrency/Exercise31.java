package com.lisong.learn.core.concurrency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise31 {

    public static void main(String[] args) throws Exception {
        int pondFactor = 5;
        if(args.length > 0)
            pondFactor = Integer.parseInt(args[0]);
        int size = 5; //number of philosopher
        if(args.length > 1)
            size = Integer.parseInt(args[1]);
        ExecutorService es = Executors.newCachedThreadPool();
        /*Chopstick[] sticks = new Chopstick[size];
        for(int i = 0; i < size; i++)
            sticks[i] = new Chopstick();
        for(int i = 0; i < size; i++) {
            if(i < size - 1)
                es.execute(new DiningPhilosophers(sticks[i], sticks[(i+1)%size], i, pondFactor));
            else
                es.execute(new DiningPhilosophers(sticks[0], sticks[i], i, pondFactor));
        }*/
        int num = 5; // number of Chopstick
        if(args.length > 2)
            num = Integer.parseInt(args[2]);
        ChopstickBin sticks = new ChopstickBin(num);
        for(int i = 0; i < size; i++)
            es.execute(new DiningPhilosophers2(sticks, i, pondFactor));
        if(args.length > 3 && args[3].equals("timeout"))
            TimeUnit.SECONDS.sleep(5);
        else {
            print("Press 'Enter' ");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
        es.shutdownNow();
    }
}

class Chopstick {
    private boolean taken = false;
    synchronized void take() throws InterruptedException {
        while(taken) {
            wait();
        }
        taken = true;
    }
    synchronized void drop() {
        taken = false;
        notifyAll();
    }
}

class DiningPhilosophers implements Runnable {
    private Chopstick left, right;
    private int id;
    private int pondFactor;
    private Random rand = new Random(88);
    DiningPhilosophers(Chopstick left, Chopstick right, int id, int pondFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.pondFactor = pondFactor;
    }

    private void pause() throws InterruptedException {
        if(pondFactor > 0)
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(pondFactor*250));
    }
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                print(this + " thinking");
                pause();
                print(this + " grabbing right");
                right.take();
                print(this + " grabbing left");
                left.take();
                print(this + " eating");
                pause();
                right.drop();
                left.drop();
            }
        }catch (InterruptedException e) {
            print(this + " interrupted");
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}

class ChopstickBin {
    private final Queue<Chopstick> sticks = new LinkedList<>();
    ChopstickBin(int stickNum) {
        if (stickNum < 2)
            print("ChopstickBin should contain at least 2 Chopsticks");
        for(int i = 0; i < stickNum; i++)
            sticks.add(new Chopstick());
    }

    synchronized Chopstick[] nextTwoChopsticks() throws InterruptedException {
        while(sticks.size() < 2) {
            wait();
        }
        Chopstick[] result = new Chopstick[2];
        result[0] = sticks.poll();
        result[1] = sticks.poll();
        return result;
    }
    synchronized void dropChopsticks(Chopstick first, Chopstick second) {
        sticks.offer(first);
        sticks.offer(second);
        notifyAll();
    }
}

class DiningPhilosophers2 implements Runnable {
    private final ChopstickBin bin;
    private int id;
    private int pondFactor;
    private Random rand = new Random(91);
    DiningPhilosophers2(ChopstickBin bin, int id, int pondFactor) {
        this.bin = bin;
        this.id = id;
        this.pondFactor = pondFactor;
    }
    private void pause() throws InterruptedException {
        if(pondFactor > 0)
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(pondFactor*250));
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                print(this + " thinking");
                pause();
                print(this + " grabbing the next two available Chopsticks");
                Chopstick[] sticks =bin.nextTwoChopsticks();
                print(this + " eating");
                pause();
                print(this + " dropping Chopsticks");
                bin.dropChopsticks(sticks[0],sticks[1]);
            }
        }catch (InterruptedException e) {
            print(this + " interrupted");
        }
    }
    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}