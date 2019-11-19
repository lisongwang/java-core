package com.lisong.learn.core.innerclasses;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 20, exercise 21.
 */
public class Exercise21 implements Intf21 {
    @Override
    public void doSomething() {
        print("Do some thing here!");
    }

    public static void main(String[] args) {

        Intf21.Nest21 nest21 = new Intf21.Nest21();
        nest21.showOuter(new Exercise21());
        Intf21.Nest21.showOuter(() -> print("Show some thing here!"));
    }
}

interface Intf21 {

    void doSomething();

    static class Nest21 {

        Nest21() { print("Nest21()"); }
        static void showOuter(Intf21 intf21) {
            intf21.doSomething();
        }
    }
}
