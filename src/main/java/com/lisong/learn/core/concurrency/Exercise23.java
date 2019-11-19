package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise23 {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new WaxOn(car));
        es.execute(new WaxOff(car));
        TimeUnit.SECONDS.sleep(5);
        es.shutdownNow();
    }
}

class Car {
    private boolean waxOn = false;
    synchronized void waxed() {
        waxOn = true;
        notify();
    }
    synchronized void buffered() {
        waxOn = false;
        notify();
    }
    synchronized void waitForWax() throws InterruptedException {
        while(!waxOn) {
            wait();
        }
    }
    synchronized void waitForBuffer() throws InterruptedException {
        while(waxOn) {
            wait();
        }
    }
}

class WaxOn implements Runnable {
    private Car car;
    WaxOn(Car car) {
        this.car = car;
    }
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                print("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffer();
            }
        }catch (InterruptedException e) {
            print("exit via interrupt");
        }
        print("Ending Wax On task");
    }
}

class WaxOff implements Runnable {
    private Car car;
    WaxOff(Car car) {
        this.car = car;
    }
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                car.waitForWax();
                print("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffered();
            }
        }catch (InterruptedException e) {
            print("exit via interrupt");
        }
        print("Ending Wax Off task");
    }
}