package com.lisong.learn.core.containers.util;

public interface SListIterator<T> {

    boolean hasNext();
    T next();
    void remove();
    void add(T t);
    void init();
}
