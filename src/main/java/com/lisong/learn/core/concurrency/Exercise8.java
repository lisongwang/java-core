package com.lisong.learn.core.concurrency;

import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class Exercise8 {

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 5; i++) {
            Thread t = new Thread(new LiftOff());
            t.setDaemon(true);
            t.start();
        }
        TimeUnit.SECONDS.sleep(3);
        print("Waiting for liftoff...");
    }
}

class LiftOff implements Runnable {
    private static int count;
    private final int id = count++;
    private int countDown = 10;

    LiftOff() {}
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }
    private String status() {
        return "#" + id + "(" + (countDown == 0 ? "Liftoff" : countDown) + "), ";
    }
    @Override
    public void run() {
        while(countDown-- > 0) {
            printnb(status());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("LiftOff " + id + " receive interrupt " + status());
                break;
            }
        }
    }
}