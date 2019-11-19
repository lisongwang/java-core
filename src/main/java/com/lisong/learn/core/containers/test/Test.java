package com.lisong.learn.core.containers.test;

public abstract class Test<C> {

    public String name;

    public Test(String name) {
        this.name = name;
    }
    public abstract long test(C container, TestParam param);
}