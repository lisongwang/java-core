package com.lisong.learn.core.generics;

import com.lisong.learn.core.generics.coffee.Coffee;import com.lisong.learn.core.generics.tuple.*;
import com.lisong.learn.core.type.factory.Part;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 15, exercise 16.
 */
public class Exercise16 {

    static TwoTuple<String, Integer> f() { return Tuple.tuple("Address", 2); }

    static TwoTuple f2() { return Tuple.tuple("Address", 2); }

    static ThreeTuple<String, Integer, Double> g() { return Tuple.tuple("Address", 2, 50.5); }

    static FourTuple<Coffee, String, Integer, Double> h() {
        return Tuple.tuple(Coffee.randomCoffee(), "Address", 2, 50.5); }

    static FiveTuple<Coffee, String, String, Integer, Double> k() {
        return Tuple.tuple(Coffee.randomCoffee(), "Address", "Remark", 2, 50.5);
    }

    static SixTuple<Part, Coffee, String, String, Integer, Double> m() {
        return Tuple.tuple(Part.randomPart(), Coffee.randomCoffee(), "Address", "Remark", 2, 50.5);
    }

    public static void main(String[] args) {

        TwoTuple<Integer, Integer> t2 = f2();
        //Integer i = t2.a;
        print(f());
        print(t2);
        print(g());
        print(h());
        print(k());
        print(m());
    }
}