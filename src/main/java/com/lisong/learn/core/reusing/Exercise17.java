package com.lisong.learn.core.reusing;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 16, exercise 17.
 */
public class Exercise17 {

    public static void main(String[] args) {

        Frog frog = new Frog();
        Amphibian.inWater(frog);
        Amphibian.onLand(frog);
    }
}

class Amphibian {

    void swim() { print("Swimming!"); }
    void walk() { print("Walking!"); }

    static void inWater(Amphibian amp) { amp.swim(); }
    static void onLand(Amphibian amp) { amp.walk(); }
}

class Frog extends Amphibian {

    @Override
    void swim() { print("Frog swimming!"); }

    @Override
    void walk() { print("Frog walking!"); }
}