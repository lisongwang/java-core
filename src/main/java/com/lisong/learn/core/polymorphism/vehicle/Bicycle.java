package com.lisong.learn.core.polymorphism.vehicle;

import com.lisong.learn.core.interfaces.CycleFactory;

import static com.lisong.learn.core.util.Print.print;

public class Bicycle implements Cycle {

    private Bicycle() {}

    @Override
    public void ride() {
        print("Bicycle ride");
    }

    @Override
    public int wheels() { return 2; }

    public void balance() { print("Bicycle balance"); }

    public static final CycleFactory factory = () -> new Bicycle();
}
