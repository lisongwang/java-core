package com.lisong.learn.core.concurrency;

import java.util.Random;
import java.util.concurrent.*;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class Exercise30 {
    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();
        BlockingQueue<Character> queue = new LinkedBlockingDeque<>();
        es.execute(new Sender(queue));
        es.execute(new Receiver(queue));
        TimeUnit.SECONDS.sleep(5);
        es.shutdownNow();
    }
}

class Sender implements Runnable {
    private Random rand = new Random(22);
    private BlockingQueue<Character> queue;
    Sender(BlockingQueue<Character> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                for(char c = 'A'; c < 'z'; c++) {
                    queue.put(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
            }
        }catch (InterruptedException e) {
            print("Sender interrupted");
        }
    }
}

class Receiver implements Runnable {
    private BlockingQueue<Character> queue;
    Receiver(BlockingQueue<Character> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                printnb("Read: " + queue.take() + ", " );
            }
        }catch (InterruptedException e) {
            print(" Receiver interrupted");
        }
    }
}