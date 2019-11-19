package com.lisong.learn.core.concurrency.common;

import static com.lisong.learn.core.util.Print.print;

public abstract class Worker<T> implements Runnable {
    protected Assembler<T> assembler;
    protected WorkerGroup<T> group;
    private boolean employed = false;

    public Worker<T> bindWorkerGroup(WorkerGroup<T> group) {
        this.group = group;
        return this;
    }
    public Worker<T> assignAssembler(Assembler<T> assembler) {
        this.assembler = assembler;
        return this;
    }

    @Override
    public void run() {
        if(group == null) {
            print("No workerGroup binding for this worker");
            return;
        }
        try {
            rest();
            while(!Thread.interrupted()) {
                doJob();
                assembler.joinWork();
                rest();
            }
        }catch (InterruptedException e) {
            print("Interrupted " + this);
        }
        print(this + " off");
    }

    abstract protected void doJob() throws InterruptedException;

    private synchronized void rest() throws InterruptedException {
        employed = false;
        assembler = null;
        group.release(this);
        while(!employed) {
            wait();
        }
    }

    public synchronized void employ() {
        employed = true;
        notifyAll();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}