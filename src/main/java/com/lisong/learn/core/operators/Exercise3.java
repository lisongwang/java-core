package com.lisong.learn.core.operators;

import static com.lisong.learn.core.util.Print.print;

/**
 * Show the aliasing during method call.
 */
public class Exercise3 {

    static void f(Letter let) {

        let.f = 6.18f;
    }

    public static void main(String[] args) {

        Letter let = new Letter();

        let.f = 28l;
        print("let.f: " + let.f);

        f(let);
        print("let.f: " + let.f);
    }
}

class Letter {

    float f;
}
