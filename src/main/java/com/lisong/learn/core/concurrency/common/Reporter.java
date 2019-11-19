package com.lisong.learn.core.concurrency.common;

import static com.lisong.learn.core.util.Print.print;

public class Reporter<T> extends Workshop<T> {

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                print(inQueue.take());
            }
        }catch (InterruptedException e) {
            print("Interrupted " + this);
        }
        print(this + " off");
    }
}