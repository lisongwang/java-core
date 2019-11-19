package com.lisong.learn.core.holding;

import com.lisong.learn.core.polymorphism.shape.RandomShapeGenerator;
import com.lisong.learn.core.polymorphism.shape.Shape;

public class Exercise31 {

    public static void main(String[] args) {

        RandomShapeGenerator gen = new RandomShapeGenerator(10);
        for(Shape s : gen) {
            s.draw();
            s.move();
            s.erase();
        }
    }
}
