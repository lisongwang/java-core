package com.lisong.learn.core.strings;

import java.io.PrintStream;
import java.util.Formatter;

public class Exercise3 {

    public static void main(String[] args) {

        PrintStream ps = System.err;
        Turtle tommy = new Turtle("Tommy", new Formatter(System.err));
        Turtle jerry = new Turtle("Jerry", new Formatter(System.err));
        tommy.move(0, 0);
        jerry.move(4, 8);
        tommy.move(3, 4);
        jerry.move(2, 5);
        tommy.move(3, 3);
        jerry.move(3, 3);
    }
}

class Turtle {

    private Formatter f;
    private String name;

    Turtle(String name, Formatter f) {
        this.name = name;
        this.f = f;
    }

    void move(int i, int j) {
        f.format("%s The Turtle is at (%d, %d)\n", name, i, j);
    }
}
