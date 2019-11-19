package com.lisong.learn.core.polymorphism.shape;

import static com.lisong.learn.core.util.Print.print;

public class Circle extends Shape {

    @Override
    public void draw() {
        print("Circle draw");
    }

    @Override
    public void erase() { print("Circle erase"); }

    @Override
    public void move() { print("Circle move"); }

    @Override
    public void dispose() {
        erase();
        super.dispose();
    }
}
