package com.lisong.learn.core.innerclasses;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 4, exercise 5.
 */
public class Exercise5 {

    public static void main(String[] args) {

        Outer5 out5 = new Outer5();
        Outer5.Inner5 inn5 = out5.new Inner5();
        inn5.getOuter().test();
    }
}

class Outer5 {

    void test() { print("test outer5"); }

    class Inner5 {

        Outer5 getOuter() { return Outer5.this; }
    }
}
