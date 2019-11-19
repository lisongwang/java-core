package com.lisong.learn.core.concurrency;

import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise18 {

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            print("interrupted from sleep");
        }
        print("Exit sleep");
    }

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Exercise18()::sleep);
        t.start();
        TimeUnit.SECONDS.sleep(2);
        t.interrupt();
    }
}