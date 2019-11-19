package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise13 implements Runnable {
    private static final int SIZE = 10;
    private static CircularSet cs = new CircularSet(1000);
    @Override
    public void run() {
        while(true) {
            int value = SerialNumberGenerator.nextSerialNumber();
            if(cs.contains(value)) {
                print("Duplicated number: " + value);
                System.exit(1);
            }
            cs.add(value);
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0; i < SIZE; i++)
            es.execute(new Exercise13());
        es.shutdown();
        if(args.length > 0) {
            TimeUnit.SECONDS.sleep(Integer.parseInt(args[0]));
            print("No duplicated number");
            System.exit(0);
        }
    }
}

class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    synchronized static int nextSerialNumber() { return serialNumber++; }
}

class CircularSet {
    private int[] a;
    private int length;
    private int index;

    CircularSet(int size) {
        a = new int[size];
        length = size;
        for(int i = 0; i < size; i++) {
            a[i] = -1;
        }
    }

    synchronized void add(int i) {
        a[index] = i;
        index = ++index%length;
    }

    synchronized boolean contains(int value) {
        for(int i = 0; i < length; i++) {
            if(a[i] == value)
                return true;
        }
        return false;
    }
}