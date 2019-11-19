package com.lisong.learn.core.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise28 {

    private static void test(String msg, BlockingQueue<LiftOff> queue) {
        print(msg);
        Thread rRunner = new Thread(new LiftOffRunner(queue));
        Thread rAdder = new Thread(new LiftOffAdder(queue));
        rRunner.start();
        rAdder.start();
        getKey("Press 'Enter' (" + msg + ")");
        rAdder.interrupt();
        while(!queue.isEmpty())
            ; // waiting for the last LiftOff out of queue
        rRunner.interrupt(); // interrupt running LiftOff
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        rRunner.interrupt(); // interrupt LiftOffRunner
        print("Finished " + msg + " test");
    }

    private static void getKey() {
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getKey(String message) {
        print(message);
        getKey();
    }

    public static void main(String[] args) {
        test("LinkedBlockingDeque", new LinkedBlockingDeque<>());
        //test("ArrayBlockingQueue", new ArrayBlockingQueue<>(3));
        //test("SynchronousQueue", new SynchronousQueue<>());
    }
}

class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> queue;
    LiftOffRunner(BlockingQueue<LiftOff> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                LiftOff liftOff = queue.take();
                liftOff.run(); //interrupted signal would be intercepted
            }
        }catch (InterruptedException e) {
            print("LiftOffRunner interrupted");
        }
    }
}

class LiftOffAdder implements Runnable {
    private BlockingQueue<LiftOff> queue;
    LiftOffAdder(BlockingQueue<LiftOff> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                queue.put(new LiftOff());
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }catch (InterruptedException e) {
            print("LiftOffAdder interrupted");
        }
    }
}