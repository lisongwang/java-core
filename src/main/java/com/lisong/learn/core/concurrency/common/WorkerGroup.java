package com.lisong.learn.core.concurrency.common;

import java.util.HashSet;
import java.util.Set;

public class WorkerGroup<T> {
    private Set<Worker<T>> workers = new HashSet<>();

    public synchronized void hire(Class<? extends Worker> workerType,
                                  Assembler<T> assembler) throws InterruptedException {
        for(Worker<T> w : workers) {
            if(w.getClass() == workerType) {
                workers.remove(w);
                w.assignAssembler(assembler).employ();
                return;
            }
        }
        wait();
        hire(workerType, assembler);
    }

    public synchronized void release(Worker<T> worker) {
        workers.add(worker);
        notifyAll();
    }
}