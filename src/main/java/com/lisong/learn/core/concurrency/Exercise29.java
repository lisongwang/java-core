package com.lisong.learn.core.concurrency;

import com.lisong.learn.core.util.Factory;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise29 {

    public static void main(String[] args) throws Exception {
        ToastQueue dryQueue = new ToastQueue(),
                bufferQueue = new ToastQueue(),
                finishQueue = new ToastQueue();
        ToastFactory toastFactory = new ToastFactory();
        SandwichesFactory sandwichesFactory = new SandwichesFactory();
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new Toaster(sandwichesFactory, dryQueue));
        es.execute(new Butterer(dryQueue, bufferQueue));
        es.execute(new Jammer(bufferQueue, finishQueue));
        es.execute(new Eater(finishQueue));
        TimeUnit.SECONDS.sleep(5);
        es.shutdownNow();
    }
}

class Toast {
    enum Status { DRY,BUTTERED,JAMMED,PEANUT,JELLY }
    enum ToastStatus {
        DRY(Status.DRY),
        BUTTERED(Status.BUTTERED,Status.PEANUT),
        JAMMED(Status.JAMMED,Status.JELLY);
        Status[] status;
        ToastStatus(Status... status) { this.status = status; }
        boolean contains(Status ss) {
            for(Status s : status) {
                if(s == ss)
                    return true;
            }
            return false;
        }
    }

    Status status = Status.DRY;
    void butter() { status = Status.BUTTERED; }
    void jam() { status = Status.JAMMED; }
    private static int count = 0;
    private final int id = count++;
    Status getStatus() { return status; }
    int getID() { return id; }

    @Override
    public String toString() {
        return "Toast " + id + ": " + status;
    }
}

class Sandwiches extends Toast {
    @Override
    void butter() { status = Status.PEANUT; }
    @Override
    void jam() { status = Status.JELLY; }

    @Override
    public String toString() {
        return "Sandwiches " + getID() + ": " + getStatus();
    }
}

class ToastFactory implements Factory<Toast> {
    @Override
    public Toast create() {
        return new Toast();
    }
}

class SandwichesFactory implements Factory<Toast> {
    @Override
    public Toast create() {
        return new Sandwiches();
    }
}

class ToastQueue extends LinkedBlockingDeque<Toast> {}

class Toaster implements Runnable {
    private Factory<Toast> toastFactory;
    private final ToastQueue toastQueue;
    Toaster(Factory<Toast> toastFactory, ToastQueue toastQueue) {
        this.toastFactory = toastFactory;
        this.toastQueue = toastQueue;
    }
    private Random rand = new Random(67);


    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                Toast t = toastFactory.create();
                print(t);
                toastQueue.put(t);
            }
        }catch (InterruptedException e) {
            print("Toaster interrupted");
        }
        print("Toaster off");
    }
}

class Butterer implements Runnable {
    private final ToastQueue dryQueue, butterQueue;
    Butterer(ToastQueue dryQueue, ToastQueue butterQueue) {
        this.dryQueue = dryQueue;
        this.butterQueue = butterQueue;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Toast t = dryQueue.take();
                t.butter();
                print(t);
                butterQueue.put(t);
            }
        }catch (InterruptedException e) {
            print("Butterer interrupted");
        }
        print("Butterer off");
    }
}

class Jammer implements Runnable {
    private final ToastQueue butterQueue, finishQueue;
    Jammer(ToastQueue butterQueue, ToastQueue finishQueue) {
        this.butterQueue = butterQueue;
        this.finishQueue = finishQueue;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Toast t = butterQueue.take();
                t.jam();
                print(t);
                finishQueue.put(t);
            }
        }catch (InterruptedException e) {
            print("Jammer interrupted");
        }
        print("Jammer off");
    }
}

class Eater implements Runnable {
    private final ToastQueue finishQueue;
    Eater(ToastQueue finishQueue) {
        this.finishQueue = finishQueue;
    }
    private int count = 0;

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Toast t = finishQueue.take();
                if(!Toast.ToastStatus.JAMMED.contains(t.getStatus()) || t.getID() != count++) {
                    print(">>>> Error: " + t);
                    System.exit(1);
                }else
                    print("Chomp! " + t);
            }
        }catch (InterruptedException e) {
            print("Eater interrupt");
        }
        print("Eater off");
    }
}