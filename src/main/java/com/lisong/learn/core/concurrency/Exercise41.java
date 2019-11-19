package com.lisong.learn.core.concurrency;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

import static com.lisong.learn.core.util.Print.print;

public class Exercise41 {

    public static void main(String[] args) {
        ActiveObjectDemo aod = new ActiveObjectDemo();
        List<Future<Void>> result = new CopyOnWriteArrayList<>();
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            es.execute(()->{
                for(int j = 0; j < 5; j++)
                    result.add(aod.handleMessage(new Message()));
            });
        }
        es.shutdown();

        while(result.size() > 0) {
            for(Future<Void> f : result) {
                if(f.isDone())
                    result.remove(f);
            }
        }

        aod.report();
        aod.shutdown();
    }
}

class ActiveObjectDemo {

    private ExecutorService es = Executors.newSingleThreadExecutor();

    private Random rand = new Random(11);

    private int totalMessage = 0;

    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        } catch (InterruptedException e) {
            print("sleep interrupted");
        }
    }

    public Future<Integer> calculateInt(int x, int y) {
        return es.submit(() -> {
            print("starting " + x + " + " + y);
            pause(500);
            return x+y;
        });
    }

    public Future<Float> calculateFloat(float x, float y) {
        return es.submit(() -> {
            print("starting " + x + " + " + y);
            pause(2000);
            return x+y;
        });
    }

    public Future<Void> handleMessage(Message message) {
        return es.submit(() -> {
            print("handler " + message);
            int current = totalMessage;
            TimeUnit.MILLISECONDS.sleep(message.delay);
            totalMessage = current + 1;
            return null;
        });
    }

    public void report() { print("Total messages be handled: " + totalMessage); }

    public void shutdown() { es.shutdown(); }
}

class Message {
    private static AtomicLong count = new AtomicLong();
    private final long id = count.getAndIncrement();
    public final long delay = ThreadLocalRandom.current().nextLong(1000);

    @Override
    public String toString() {
        return "message-" + id;
    }
}