package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise22 {
    private volatile boolean flag = false;
    boolean testFlag() { return flag; }
    void setFlag(boolean flag) { this.flag = flag; }

    public static void main(String[] args) throws Exception {
        Exercise22 exe22 = new Exercise22();
        ExecutorService es = Executors.newCachedThreadPool();
        //es.execute(new BusyTask(exe22));
        //es.execute(new BusyWait(exe22));
        es.execute(new BlockTask(exe22));
        es.execute(new BlockWait(exe22));
        TimeUnit.MILLISECONDS.sleep(30);
        es.shutdownNow();
    }
}

class BusyTask implements Runnable {
    private final Exercise22 exe22;
    BusyTask(Exercise22 exe22) {
        this.exe22 = exe22;
    }
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.NANOSECONDS.sleep(3);
                synchronized (exe22) {
                    exe22.setFlag(true);
                }
            }
        }catch (InterruptedException e) {
            print("interrupted from sleep");
        }
    }
}

class BusyWait implements Runnable {
    private final Exercise22 exe22;
    BusyWait(Exercise22 exe22) {
        this.exe22 = exe22;
    }
    @Override
    public void run() {
        while(!Thread.interrupted()) {
            while(!exe22.testFlag())
                ;//blank loop
            synchronized (exe22) {
                exe22.setFlag(false);
                print("Set flag to false");
            }
        }
    }
}

class BlockTask implements Runnable {
    private final Exercise22 exe22;
    BlockTask(Exercise22 exe22) {
        this.exe22 = exe22;
    }
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.NANOSECONDS.sleep(3);
                synchronized (exe22) {
                    exe22.setFlag(true);
                    exe22.notifyAll();
                }
            }
        }catch (InterruptedException e) {
            print("interrupted from sleep");
        }
    }
}

class BlockWait implements Runnable {
    private final Exercise22 exe22;
    BlockWait(Exercise22 exe22) {
        this.exe22 = exe22;
    }
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized (exe22) {
                    while(!exe22.testFlag()) {
                        exe22.wait();
                    }
                    exe22.setFlag(false);
                    print("Set flag to false");
                }
            }
        }catch (InterruptedException e) {
            print("interrupted from wait");
        }
    }
}