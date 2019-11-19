package com.lisong.learn.core.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.lisong.learn.core.util.Print.print;

/**
 * The atomic version is much slower than the lock version
 * because the probability of collision on the same element
 * is very small thus the lock hardly block any thread.
 */
public class Exercise39 {

    public static void main(String[] args) throws Exception {
        FastSimulation.testEvolve();
    }
}

class FastSimulation {
    static final int N_ELEMENTS = 100000;
    static final int N_GENES = 30;
    static final int N_EVOLVE = 50;
    static final int N_CYCLES = 10000;
    static final CyclicBarrier barrier = new CyclicBarrier(N_EVOLVE+1);
    static final ExecutorService es = Executors.newCachedThreadPool();

    public static void testEvolve() throws Exception {
        print("%-20s %14s\n", "Simulation", "Time(ms)");
        Instant start = Instant.now();
        for(int i = 0; i < N_EVOLVE; i++)
            es.execute(new EvolveWithAtomic());
        barrier.await();
        long duration = Duration.between(start, Instant.now()).toMillis();
        print("%-20s %14d\n", "EvolveWithAtomic", duration);

        start = Instant.now();
        for(int i = 0; i < N_EVOLVE; i++)
            es.execute(new Evolve());
        barrier.await();
        duration = Duration.between(start, Instant.now()).toMillis();
        print("%-20s %14d\n", "Evolve", duration);

        es.shutdown();
    }

    static class EvolveWithAtomic implements Runnable {
        static final AtomicInteger[][] GRID = new AtomicInteger[N_ELEMENTS][N_GENES];
        static {
            for(int i = 0; i < N_ELEMENTS; i++) {
                for(int j = 0; j < N_GENES; j++)
                    GRID[i][j] = new AtomicInteger(ThreadLocalRandom.current().nextInt(1000));
            }
        }

        @Override
        public void run() {
            for(int o = 0; o < N_CYCLES; o++) {
                int element = ThreadLocalRandom.current().nextInt(N_ELEMENTS);
                for(int i = 0; i < N_GENES; i++) {
                    int oldValue = GRID[element][i].get();
                    int previous = element - 1;
                    if(previous < 0)
                        previous = N_ELEMENTS - 1;
                    int next = element + 1;
                    if(next >= N_ELEMENTS)
                        next = 0;
                    int newValue = (GRID[previous][i].get() + oldValue + GRID[next][i].get())/3;
                    if(!GRID[element][i].compareAndSet(oldValue, newValue))
                        print("Old value changed from " + oldValue);
                }
            }
            try {
                barrier.await();
            } catch (InterruptedException e) {
                //normal exit
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class Evolve implements Runnable {
        static final int[][] INT = new int[N_ELEMENTS][N_GENES];
        static {
            for(int i = 0; i < N_ELEMENTS; i++) {
                for(int j = 0; j < N_GENES; j++)
                    INT[i][j] = ThreadLocalRandom.current().nextInt(1000);
            }
        }

        @Override
        public void run() {
            for(int o = 0; o < N_CYCLES; o++) {
                int element = ThreadLocalRandom.current().nextInt(N_ELEMENTS);
                synchronized (INT[element]) {
                    for(int i = 0; i < N_GENES; i++) {
                        int oldValue = INT[element][i];
                        int previous = element - 1;
                        if(previous < 0)
                            previous = N_ELEMENTS - 1;
                        int next = element + 1;
                        if(next >= N_ELEMENTS)
                            next = 0;
                        int newValue = (INT[previous][i] + oldValue + INT[next][i])/3;
                        INT[element][i] = newValue;
                    }
                }
            }
            try {
                barrier.await();
            } catch (InterruptedException e) {
                //normal exit
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}