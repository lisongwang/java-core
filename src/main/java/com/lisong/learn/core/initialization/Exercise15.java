package com.lisong.learn.core.initialization;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 13, exercise 14, exercise 15.
 */
public class Exercise15 {

    static String s1 = "init s1";
    static {
        s2 = s1 + " init s2";
    }
    static String s2;

    static void printStatic() {
        print("s1: " + s1);
        print("s2: " + s2);
    }

    public static void main(String[] args) {

        print("Inside main()");
        Cups.cup1.f(99);
        printStatic();

        Mugs mugs = new Mugs(999);
        mugs.say();
    }

    static Cups cups1 = new Cups();
    static Cups cups2 = new Cups();
}

class Cup {

    Cup(int mark) {
        print("Cup (" + mark + ")");
    }

    void f(int mark) {
        print("f (" + mark + ")");
    }
}

class Cups {

    static {
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }

    static Cup cup1;
    static Cup cup2;

    Cups () {
        print("Cups()");
    }
}

class Mugs {

    String s;
    {
        s = "hello";
        print("instance initialize string");
    }
    Cup cup1;
    Cup cup2;
    {
        cup1 = new Cup(3);
        cup2 = new Cup(4);
    }

    Mugs(int i) {
        print("Mugs (" + i + ")");
    }

    void say() {
        print(s);
    }
}