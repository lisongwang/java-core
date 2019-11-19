package com.lisong.learn.core.concurrency;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise21 {

    public static void main(String[] args) throws Exception {
        MessageShower mh = new MessageShower();
        MessagePrepare mp = new MessagePrepare(mh);
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(mh);
        es.execute(mp);
        TimeUnit.SECONDS.sleep(5);
        print("halt now");
        es.shutdownNow();
    }
}

class MessageShower implements Runnable {
    private volatile String time;
    void showTime(String time) { this.time = time; }
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized (this) {
                    wait();
                    print("Welcome to Beijing " + time);
                }
            }
        }catch (InterruptedException e) {
            print("Interrupted from wait");
        }
    }
}

class MessagePrepare implements Runnable {
    private final MessageShower shower;
    MessagePrepare(MessageShower shower) {
        this.shower = shower;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(1000);
                synchronized (shower) {
                    shower.showTime(LocalDateTime.now().toString());
                    shower.notifyAll();
                }
            }
        }catch (InterruptedException e) {
            print("Interrupted from sleep");
        }
    }
}