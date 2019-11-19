package com.lisong.learn.core.concurrency;

import com.lisong.learn.core.generics.coffee.Coffee;
import com.lisong.learn.core.generics.coffee.CoffeeGenerator;
import com.lisong.learn.core.util.Generator;

import java.util.List;
import java.util.concurrent.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise34 {

    public static void main(String[] args) throws Exception {
        Exchanger<List<Coffee>> ex = new Exchanger<>();
        List<Coffee> producerList = new CopyOnWriteArrayList<>(),
                     consumerList = new CopyOnWriteArrayList<>();
        ExecutorService es = Executors.newCachedThreadPool();
        int size = 10;
        if(args.length > 0)
            size = Integer.parseInt(args[0]);
        long delay = 100;
        if(args.length > 1)
            delay = Long.parseLong(args[1]);

        es.execute(new ExchangerProducer<>(ex, producerList, new CoffeeGenerator(), size));
        es.execute(new ExchangerConsumer<>(ex, consumerList));
        TimeUnit.MILLISECONDS.sleep(delay);
        es.shutdownNow();
    }
}

class ExchangerProducer<T> implements Runnable {
    private Exchanger<List<T>> ex;
    private List<T> holder;
    private Generator<T> gen;
    private int size;
    ExchangerProducer(Exchanger<List<T>> ex, List<T> holder, Generator<T> gen, int size) {
        this.ex = ex;
        this.holder = holder;
        this.gen = gen;
        this.size = size;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                for(int i = 0; i < size; i++)
                    holder.add(gen.next());
                holder = ex.exchange(holder);
            }
        }catch (InterruptedException e) {
            print("ExchangerProducer exit from interruption");
        }
    }
}

class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>> ex;
    private List<T> holder;
    private volatile T value;
    ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder) {
        this.ex = ex;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                holder = ex.exchange(holder);
                for(T t : holder) {
                    value = t;
                    holder.remove(t);
                }
            }
        }catch (InterruptedException e) {
            print("ExchangerConsumer exit from interruption");
        }
        print("Final value: " + value);
    }
}