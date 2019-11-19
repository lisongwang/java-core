package com.lisong.learn.core.polymorphism.vehicle;

import com.lisong.learn.core.interfaces.CycleFactory;

import static com.lisong.learn.core.util.Print.print;

public class Unicycle implements Cycle {

    private Unicycle() {}

    @Override
    public void ride() {
        print("Unicycle ride");
    }

    @Override
    public int wheels() { return 1; }

    public void balance() { print("Unicycle balance"); }

    public static final CycleFactory factory = () -> new Unicycle();
}
