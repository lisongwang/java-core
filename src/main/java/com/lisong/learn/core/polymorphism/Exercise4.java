package com.lisong.learn.core.polymorphism;

import com.lisong.learn.core.polymorphism.shape.RandomShapeGenerator;
import com.lisong.learn.core.polymorphism.shape.Shape;

/**
 * Combine exercise 2, exercise 3, exercise 4.
 */
public class Exercise4 {

    private static RandomShapeGenerator gen = new RandomShapeGenerator();

    public static void main(String[] args) {

        Shape[] s = new Shape[9];
        for(int i = 0; i < s.length; i++) {
            s[i] = gen.next();
        }
        for(Shape shape : s) {
            shape.draw();
            shape.erase();
            shape.move();
        }
    }
}
