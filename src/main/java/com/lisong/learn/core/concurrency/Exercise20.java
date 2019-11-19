package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise20 {

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++)
            es.execute(new LiftOff());
        TimeUnit.MILLISECONDS.sleep(700);
        es.shutdownNow();
        if(!es.awaitTermination(300, TimeUnit.MILLISECONDS))
            print("Some tasks were not terminated");
    }
}