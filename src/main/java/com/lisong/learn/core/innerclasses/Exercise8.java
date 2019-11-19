package com.lisong.learn.core.innerclasses;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 7, exercise 8.
 */
public class Exercise8 {

    private int i = 1;

    private void saySomething() {
        print("i = " + i);
    }

    class Inner7 {

        private int j = 2;

        void changeOuter() {
            Exercise8.this.i++;
            Exercise8.this.saySomething();
        }

        void show() { print("j = " + j); }
    }

    void doIt() {
        new Inner7().changeOuter();
    }

    Inner7 changeInner() {
        Inner7 in7 = new Inner7();
        in7.j++;
        return in7;
    }

    public static void main(String[] args) {

        Exercise8 exe7 = new Exercise8();
        exe7.doIt();
        exe7.changeInner().show();
    }
}

class Inner8 {

    public static void main(String[] args) {
        Exercise8 exe7 = new Exercise8();
        exe7.doIt();
        exe7.changeInner().show(); // can't access if Inner7 is private or show() is private
    }
}