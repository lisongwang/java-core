package com.lisong.learn.core.type;

import java.util.Arrays;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 3, exercise 4, exercise 5, exercise 6.
 */
public class Exercise6 {

    static void rotate(Shape s) {
        if(s instanceof Cicle)
            return;
        print(s.getClass().getSimpleName() + ".rotate()");
    }

    static void highlight (Shape s) {
        // highlight all the triangle
        if(s instanceof Triangle) {
            s.highlighted = true;
        }
    }

    public static void main(String[] args) {

        List<Shape> shapes = Arrays.asList(new Cicle(), new Squre(), new Triangle(), new Rhomboid());
        Rhomboid h = null;
        Cicle c = null;
        for(Shape s : shapes) {
            if(s instanceof Rhomboid)
                h = (Rhomboid)s;
            if(s instanceof Cicle)
                c = (Cicle)s;
            //rotate(s);
            highlight(s);
            print(s);
        }
    }
}

abstract class Shape {
    protected boolean highlighted = false;
    void draw() { print(this + ".draw()"); }
    public abstract String toString();
}

class Cicle extends Shape {
    @Override
    public String toString() {
        return "Cicle " + (highlighted?"is ":"is not ") + "highlighted";
    }
}

class Squre extends Shape {
    @Override
    public String toString() {
        return "Squre " + (highlighted?"is ":"is not ") + "highlighted";
    }
}

class Triangle extends Shape {
    @Override
    public String toString() {
        return "Triangle " + (highlighted?"is ":"is not ") + "highlighted";
    }
}

class Rhomboid extends Shape {
    @Override
    public String toString() {
        return "Rhomboid " + (highlighted?"is ":"is not ") + "highlighted";
    }
}
