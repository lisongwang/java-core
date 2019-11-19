package com.lisong.learn.core.polymorphism.shape;

import static com.lisong.learn.core.util.Print.print;

public class Square extends Shape {

    @Override
    public void draw() {
        print("Square draw");
    }

    @Override
    public void erase() {
        print("Square erase");
    }

    @Override
    public void move() { print("Square move"); }
}
