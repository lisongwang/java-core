package com.lisong.learn.core.reusing;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 7, exercise 8. (exercise 7 implemented in Exercise5.java)
 */
public class Exercise8 {

    public static void main(String[] args) {

        Sub sub = new Sub();
    }
}

class Base {

    Base(int i) { print("Base constructor with arg"); }
}

class Sub extends Base {

    Sub(int i) {
        super(i);
        print("Sub constructor with arg");
    }

    Sub() {
        super(1);
        print("Sub default constructor");
    }
}
