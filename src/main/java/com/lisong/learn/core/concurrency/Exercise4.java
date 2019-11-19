package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.lisong.learn.core.util.Print.print;

public class Exercise4 {

    private static void runByExecutor(ExecutorService es, Runnable... tasks) {
        for(Runnable task : tasks)
            es.execute(task);
        es.shutdown();
        for(Exercise2 exe2 : (Exercise2[])tasks) {
            if(exe2.isComplete())
                print(exe2.getResult());
            else
                print("Not complete for " + exe2);
        }
    }

    private static void runForTask() {
        Exercise2[] tasks = {new Exercise2(20), new Exercise2(20), new Exercise2(20), new Exercise2(20)};
        runByExecutor(Executors.newCachedThreadPool(), tasks);
        tasks = new Exercise2[]{new Exercise2(20), new Exercise2(20), new Exercise2(20), new Exercise2(20)};
        runByExecutor(Executors.newFixedThreadPool(2), tasks);
        tasks = new Exercise2[]{new Exercise2(20), new Exercise2(20), new Exercise2(20), new Exercise2(20)};
        runByExecutor(Executors.newSingleThreadExecutor(), tasks);
    }

    public static void main(String[] args) {
        runForTask();
    }
}