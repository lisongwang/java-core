package com.lisong.learn.core.concurrency.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static com.lisong.learn.core.util.Print.print;

public class HorseRace {
    private static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService es = Executors.newCachedThreadPool();
    public HorseRace(int nHorses, int pause) {
        CyclicBarrier barrier = new CyclicBarrier(nHorses, () -> {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < FINISH_LINE; i++)
                sb.append("=");
            print(sb);
            for(Horse h : horses)
                print(h.tracks());
            for(Horse h : horses) {
                if(h.getStride() >= FINISH_LINE) {
                    print(h + "won!");
                    es.shutdownNow();
                    return;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        for(int i = 0; i < nHorses; i++) {
            Horse h = new Horse(barrier);
            horses.add(h);
            es.execute(h);
        }
    }

    public static void main(String[] args) {
        new HorseRace(10, 100);
    }
}

class Horse implements Runnable {
    private static int count = 0;
    private final int id = count++;
    private int stride = 0;
    private static Random rand = new Random();
    private CyclicBarrier barrier;
    synchronized int getStride() { return stride; }
    Horse(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized (this) {
                    stride += rand.nextInt(3);
                }
                barrier.await();
            }
        } catch(InterruptedException e) {
            print(this + " interrupted");
        } catch(BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Horse " + id + " ";
    }

    String tracks() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < getStride(); i++)
            sb.append("*");
        sb.append(id);
        return sb.toString();
    }
}