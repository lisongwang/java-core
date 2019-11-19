package com.lisong.learn.core.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise14 {

    public static void main(String[] args) throws Exception {
        List<Timer> timers = new ArrayList<>();
        for(int i = 0; i < 1000; i++)
            timers.add(new Timer());
        for(Timer timer : timers) {
            timer.schedule(new TimerTask() {
                volatile double d;
                @Override
                public void run() {
                    for(int j = 0; j < 10; j++)
                        d = (Math.PI + Math.E)/Math.PI;
                }
            }, 1000);
        }
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(7);
                for(Timer timer : timers)
                    timer.cancel();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        while(true) {
            TimeUnit.SECONDS.sleep(1);
            print(Thread.activeCount());
            if(Thread.activeCount() <= 2) break;
        }
    }
}