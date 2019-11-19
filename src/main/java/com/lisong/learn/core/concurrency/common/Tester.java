package com.lisong.learn.core.concurrency.common;

import com.lisong.learn.core.util.Generators;
import com.lisong.learn.core.util.RandomGenerator;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.lisong.learn.core.util.Print.print;

public abstract class Tester<C> {

    static int testReps = 10;
    static int testCycles = 1000;
    protected static int containerSize = 1000;

    protected abstract C containerInitializer();
    protected abstract void startReaderAndWriter();

    C testContainer;
    String testID;
    int nReaders;
    int nWriters;
    volatile long readResult = 0;
    volatile long readTime = 0;
    volatile long writeTime = 0;
    CountDownLatch endLatch;
    public static ExecutorService es = Executors.newCachedThreadPool();
    Integer[] writeData;
    public Tester(String testID, int nReaders, int nWriters) {
        this.testID = testID;
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        writeData = Generators.fillArray(Integer.class, new RandomGenerator.Integer(), containerSize);
        for(int i = 0; i < testReps; i++) {
            runTest();
            readTime = 0;
            writeTime = 0;
        }
    }

    void runTest() {
        endLatch = new CountDownLatch(nReaders + nWriters);
        testContainer = containerInitializer();
        startReaderAndWriter();
        try {
            endLatch.await();
        }catch (InterruptedException e) {
            print("endLatch Interrupted");
        }
        print("%-27s %14d %14d\n", testID + " " + nReaders + "r " + nWriters + "w", readTime, writeTime);
        if(readTime != 0 && writeTime != 0)
            print("%-27s %14d\n", "readTime + writeTime =", readTime + writeTime);
    }

    abstract class TestTask implements Runnable {
        abstract void test();
        abstract void putResults();
        long duration;
        @Override
        public void run() {
            Instant startTime = Instant.now();
            test();
            duration = Duration.between(startTime, Instant.now()).toNanos();
            synchronized (Tester.this) {
                putResults();
            }
            endLatch.countDown();
        }
    }

    public static void initMain(String[] args) {
        if(args.length > 0)
            testReps = Integer.parseInt(args[0]);
        else if(args.length > 1)
            testCycles = Integer.parseInt(args[1]);
        else if(args.length > 2)
            containerSize = Integer.parseInt(args[2]);
        print("%-27s %14s %14s\n", "Type", "Read time", "Write time");
    }
}