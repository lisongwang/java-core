package com.lisong.learn.core.generics;

import com.lisong.learn.core.generics.coffee.Coffee;
import com.lisong.learn.core.type.factory.Part;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 9, exercise 10.
 */
public class Exercise10 {

    private <A, B, C> void f(A a, B b, C c, Part p) {

        print(a.getClass().getSimpleName());
        print(b.getClass().getSimpleName());
        print(c.getClass().getSimpleName());
        print(p.getClass().getSimpleName());
    }

    public static void main(String[] args) {

        Exercise10 exe10 = new Exercise10();
        exe10.f("OK", 5, 10.2, Part.randomPart());
        exe10.f(false, Coffee.randomCoffee(), 'A', Part.randomPart1());
    }
}
