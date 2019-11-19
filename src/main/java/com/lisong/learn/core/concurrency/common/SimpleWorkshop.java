package com.lisong.learn.core.concurrency.common;

import static com.lisong.learn.core.util.Print.print;

public class SimpleWorkshop<T> extends Workshop<T> {
    protected T t;

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                t = inQueue.take();
                process();
                outQueue.offer(t);
            }
        }catch (InterruptedException e) {
            print("Interrupted " + this);
        }
        print(this + " off");
    }

    //This method should always be overridden by subclass
    protected void process() throws InterruptedException {
        throw new UnsupportedOperationException();
    }
}