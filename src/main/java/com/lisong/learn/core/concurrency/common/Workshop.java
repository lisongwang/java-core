package com.lisong.learn.core.concurrency.common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Workshop<T> implements Runnable {

    public enum State { RUNNING, STOP }
    protected State state;

    protected ExecutorService executorService = Executors.newCachedThreadPool();

    protected volatile BlockingQueue<T> inQueue;
    protected volatile BlockingQueue<T> outQueue;

    public Workshop<T> bindInQueue(BlockingQueue<T> inQueue) {
        this.inQueue = inQueue;
        return this;
    }
    public Workshop<T> bindOutQueue(BlockingQueue<T> outQueue) {
        this.outQueue = outQueue;
        return this;
    }

    public synchronized void start() {
        state = State.RUNNING;
        executorService.execute(this);
    }
    public synchronized void stop() {
        state = State.STOP;
        executorService.shutdownNow();
    }
}