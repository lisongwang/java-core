package com.lisong.learn.core.innerclasses.facade;

public interface Selector<T> {

    boolean end();
    T current();
    void next();
}
