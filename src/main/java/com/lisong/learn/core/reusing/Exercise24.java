package com.lisong.learn.core.reusing;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 23, exercise 24.
 */
public class Exercise24 {

    public static void main(String[] args) {

        //Beetle b1 = new Beetle();
        //Beetle b2 = new Beetle();
        Beetle.printInit("Beetle main");
        Beetle.fly();

        LadyBug lb = new LadyBug();
    }
}

class Insect {

    private int i = 9;
    protected int j;
    Insect() {
        print("i = " + i + " j = " + j);
        j = 39;
    }

    private static int x1 = printInit("Static Insect.x1 initialized");

    static int printInit(String s) {
        print(s);
        return 47;
    }
}

class Beetle extends Insect {

    private int k = printInit("Beetle.k initialized");

    Beetle() {
        print("k = " + k);
        print("j = " + j);
    }

    private static int x2 = printInit("static Beetle.x2 initialized");

    static void fly() {
        print("Beetle fly");
    }
}

class LadyBug extends Beetle {

    private int n = printInit("LadyBug.n initialized");

    LadyBug() {
        print("n = " + n);
        print("j = " + j);
    }

    private static int x3 = printInit("static LadyBug.x3 initialized");
}