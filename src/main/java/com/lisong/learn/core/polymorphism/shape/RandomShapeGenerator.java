package com.lisong.learn.core.polymorphism.shape;

import java.util.Iterator;
import java.util.Random;

public class RandomShapeGenerator implements Iterable<Shape> {

    private Random rand = new Random(100);

    private int size = 0;

    public RandomShapeGenerator() {}

    public RandomShapeGenerator(int size) { this.size = size; }

    public Shape next() {
        switch(rand.nextInt(4)) {
            default:
            case 0: return new Circle();
            case 1: return new Square();
            case 2: return new Triangle();
            case 3: return new Polygon();
        }
    }

    @Override
    public Iterator<Shape> iterator() {
        return new Iterator<Shape>() {
            private int nextIndex = 0;
            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public Shape next() {
                nextIndex++;
                return RandomShapeGenerator.this.next();
            }
        };
    }
}
