package com.lisong.learn.core.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise17 {

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();
        RadiationCounter rc = new RadiationCounter(es);
        for(int i = 0; i < 5; i++)
            rc.add(new RemoteSensor(rc));
        TimeUnit.SECONDS.sleep(1);
        for(int i = 0; i < 5; i++)
            rc.add(new RemoteSensor(rc));
        TimeUnit.SECONDS.sleep(1);
        rc.cancel();
        es.shutdown();
        if(!es.awaitTermination(1, TimeUnit.SECONDS))
            print("some sensors were not terminated");
        print("Total radiation count: " + rc.getCount());
        print("Sum of sensor local count: " + rc.countSensor());
    }
}

class RadiationCounter {
    private ExecutorService es;
    private int count = 0;
    synchronized int getCount() { return count; }
    synchronized void increment() { count++; }
    private volatile boolean cancel = false;
    void cancel() { cancel = true; }
    boolean isCancel() { return cancel; }
    private List<RemoteSensor> sensors = new ArrayList<>();
    RadiationCounter(ExecutorService es) {
        this.es = es;
    }
    void add(RemoteSensor sensor) {
        if(!cancel) {
            es.execute(sensor);
            sensors.add(sensor);
        }
    }
    int countSensor() {
        int sum = 0;
        for(RemoteSensor sensor : sensors)
            sum += sensor.getLocalCount();
        return sum;
    }
}

class RemoteSensor implements Runnable {
    private static Random rand = new Random(85);
    private static int count = 0;
    private final int id = count++;
    private RadiationCounter counter;
    private int localCount = 0;
    RemoteSensor(RadiationCounter counter) {
        this.counter = counter;
    }
    int getLocalCount() { return localCount; }
    @Override
    public void run() {
        while(!counter.isCancel()) {
            try {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(100));
            } catch (InterruptedException e) {
                print("interrupted sensor: " + id + " with last local count: " +
                        localCount + "  Total count: " + counter.getCount());
                return;
            }
            localCount++;
            counter.increment();
            print("sensor: " + id + " with current local count: " + localCount +
                    "  Total count: " + counter.getCount());
        }
        print("cancel sensor: " + id + " with last local count: " + localCount +
                "  Total count: " + counter.getCount());
    }
}