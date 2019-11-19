package com.lisong.learn.core.polymorphism.vehicle;

import com.lisong.learn.core.interfaces.CycleFactory;

import static com.lisong.learn.core.util.Print.print;

public class Tricycle implements Cycle {

    private Tricycle() {}

    @Override
    public void ride() {
        print("Tricycle ride");
    }

    @Override
    public int wheels() { return 3; }

    public static final CycleFactory factory = () -> new Tricycle();
}
