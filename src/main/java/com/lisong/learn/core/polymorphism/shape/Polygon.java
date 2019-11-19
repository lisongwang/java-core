package com.lisong.learn.core.polymorphism.shape;

import static com.lisong.learn.core.util.Print.print;

public class Polygon extends Shape {

    @Override
    public void draw() {
        print("Polygon draw");
    }

    @Override
    public void erase() {
        print("Polygon erase");
    }

    @Override
    public void move() { print("Polygon move"); }

    @Override
    public void dispose() {
        erase();
        super.dispose();
    }
}
