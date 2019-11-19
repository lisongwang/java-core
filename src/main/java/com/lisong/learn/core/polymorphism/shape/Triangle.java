package com.lisong.learn.core.polymorphism.shape;

import static com.lisong.learn.core.util.Print.print;

public class Triangle extends Shape {

    @Override
    public void draw() {
        print("Triangle draw");
    }

    @Override
    public void erase() {
        print("Triangle erase");
    }

    @Override
    public void move() { print("Triangle move"); }

    @Override
    public void dispose() {
        erase();
        super.dispose();
    }
}
