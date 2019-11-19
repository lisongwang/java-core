package com.lisong.learn.core.innerclasses;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 16, exercise 17, exercise 18.
 */
public class Exercise18 {

    public static void main(String[] args) {

        Outer18.Inner18 i8 = Outer18.getInner();
        i8.changeOuter(8);
        i8.showOuter();

        Outer18.Inner18 i18 = new Outer18.Inner18();
        i18.changeOuter(18);
        i18.showOuter();
    }
}

class Outer18 {

    static private int i;

    Outer18() { print("Constructing Outer18"); }

    static class Inner18 {
        void changeOuter(int j) { i = j; }
        void showOuter() {
            print("Outer18.i = " + i);
        }
    }

    static Inner18 getInner() {
        return new Inner18();
    }
}