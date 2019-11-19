package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise24 {

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();
        StoreBuffer<Item> buffer = new StoreBuffer<>(1);
        es.execute(new Producer(buffer));
        es.execute(new Consumer(buffer));
        TimeUnit.SECONDS.sleep(2);
        es.shutdownNow();
    }
}

class StoreBuffer<T> {
    private final int length;
    private final Object[] items;
    private int head;
    private int tail;
    StoreBuffer(int size) {
        length = size+1;
        items = new Object[length];
        head = 0;
        tail = 0;
    }

    synchronized void store(T t) throws InterruptedException {
        while((tail+1)%length == head) {
            wait();
        }
        notifyAll();
        items[tail] = t;
        tail = (tail+1)%length;
    }

    @SuppressWarnings("unchecked")
    synchronized T get() throws InterruptedException {
        while(head == tail) {
            wait();
        }
        notifyAll();
        T t = (T)items[head];
        head = (head+1)%length;
        return t;
    }
}

class Item {
    private static int count = 0;
    private final int id = count++;
    @Override
    public String toString() {
        return "Item: " + id;
    }
}

class Producer implements Runnable {
    private StoreBuffer<Item> buffer;
    Producer(StoreBuffer<Item> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                buffer.store(new Item());
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (InterruptedException e) {
            print("interrupted from Producer");
        }
    }
}

class Consumer implements Runnable {
    private StoreBuffer<Item> buffer;
    Consumer(StoreBuffer<Item> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Item item = buffer.get();
                TimeUnit.MILLISECONDS.sleep(50);
                print("consumer " + item);
            }
        }catch (InterruptedException e) {
            print("interrupted from Consumer");
        }
    }
}