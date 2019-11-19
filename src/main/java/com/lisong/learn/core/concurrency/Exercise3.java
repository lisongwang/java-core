package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise3 {

    private static void runByExecutor(ExecutorService es, Runnable... tasks) {
        for(Runnable task : tasks)
            es.execute(task);
        es.shutdown();
    }
    private static void runForTask() throws InterruptedException {
        Runnable[] tasks = {new Exercise1(), new Exercise1(), new Exercise1(), new Exercise1()};
        runByExecutor(Executors.newCachedThreadPool(), tasks);
        TimeUnit.MILLISECONDS.sleep(1000);
        runByExecutor(Executors.newFixedThreadPool(2), tasks);
        TimeUnit.MILLISECONDS.sleep(1000);
        runByExecutor(Executors.newSingleThreadExecutor(), tasks);
    }

    public static void main(String[] args) throws InterruptedException {
        runForTask();
    }
}