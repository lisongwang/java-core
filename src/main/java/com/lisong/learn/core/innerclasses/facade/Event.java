package com.lisong.learn.core.innerclasses.facade;

public abstract class Event {

    private long eventTime;
    protected final long delayTime;
    public Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }

    public void start() {
        this.eventTime = System.currentTimeMillis() + delayTime;
    }

    public boolean ready() {
        return System.currentTimeMillis() >= eventTime;
    }

    public abstract void action();
}
