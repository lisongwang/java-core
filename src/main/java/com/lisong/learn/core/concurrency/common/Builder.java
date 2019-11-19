package com.lisong.learn.core.concurrency.common;

import com.lisong.learn.core.util.Factory;

import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Builder<T> extends Workshop<T> {
    private Factory<T> factory;
    private long buildInterval;

    public void bindFactory(Factory<T> factory) {
        this.factory = factory;
    }

    public void setBuildInterval(long buildInterval, TimeUnit unit) {
        this.buildInterval = TimeUnit.MICROSECONDS.convert(buildInterval, unit);
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MICROSECONDS.sleep(buildInterval);
                T t = factory.create();
                print(this + " created " + t);
                outQueue.put(t);
            }
        }catch (InterruptedException e) {
            print("Interrupted " + this);
        }
        print(this + " off");
    }
}