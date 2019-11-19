package com.lisong.learn.core.concurrency.common;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.lisong.learn.core.util.Print.print;

public class Assembler<T> extends Workshop<T> {

    protected Class<? extends Worker<T>>[] workers;
    protected WorkerGroup<T> group;
    private CyclicBarrier barrier;
    private T t;
    private ExecutorService workerEs = Executors.newCachedThreadPool();

    @SuppressWarnings("unchecked")
    public Assembler bindWorkers(Class<? extends Worker<T>>... workers) {
        this.workers = workers;
        return this;
    }
    public Assembler bindWorkGroup(WorkerGroup<T> group) {
        this.group = group;
        return this;
    }

    public void joinWork() throws InterruptedException {
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    public T getItem() {
        return t;
    }

    @Override
    public synchronized void start() {
        if(group == null) {
            group = new WorkerGroup<>();
        }
        for(Class<? extends Worker<T>> type : workers) {
            try {
                workerEs.execute(type.newInstance().bindWorkerGroup(group));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        this.barrier = new CyclicBarrier(workers.length+1);
        super.start();
    }

    @Override
    public synchronized void stop() {
        workerEs.shutdownNow();
        super.stop();
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                t = inQueue.take();
                synchronized (this) {
                    if(state == State.RUNNING) {
                        for(Class<? extends Worker> rt : workers) {
                            group.hire(rt, this);
                        }
                        joinWork();
                    }else {
                        break;
                    }
                }
                outQueue.offer(t);
            }
        }catch (InterruptedException e) {
            print("Interrupted " + this);
        }
        print(this + " off");
    }
}