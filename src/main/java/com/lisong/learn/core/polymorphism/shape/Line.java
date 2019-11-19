package com.lisong.learn.core.polymorphism.shape;

import static com.lisong.learn.core.util.Print.print;

public class Line extends Shape {

    private int start, end;

    public Line(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw() {
        print("Drawing Line: " + start +", " + end);
    }

    @Override
    public void erase() {
        print("Erasing Line: " + start +", " + end);
    }

    @Override
    public void move() { print("Moving Line: " + start +", " + end); }

    @Override
    public void dispose() {
        erase();
        super.dispose();
    }
}
