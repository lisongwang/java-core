package com.lisong.learn.core.innerclasses.facade;

public interface EventFactory<T> {
    Event produce(T t, Long delay);
}