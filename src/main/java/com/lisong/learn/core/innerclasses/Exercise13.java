package com.lisong.learn.core.innerclasses;

import com.lisong.learn.core.innerclasses.facade.Intf9;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 12, exercise 13.
 */
public class Exercise13 {

    static Intf9 getIntf9() {

        return new Intf9() {
            @Override
            public void showSomething() {
                print("anonymous inner!");
            }
        };
    }

    public static void main(String[] args) {

        Outer7 outer7 = new Outer7();
        outer7.processInner7();

        Intf9 intf9 = getIntf9();
        intf9.showSomething();
    }
}

class Outer7 {

    private int i = 10;
    private void saySomething() {
        print("i = " + i);
    }

    class Inner7 {

        private int j = 20;

        void changeOuter() {
            Outer7.this.i++;
            Outer7.this.saySomething();
        }

        private void show() { print("j = " + j); }
    }

    void processInner7() {

        Inner7 inner7 = new Inner7() {

            @Override
            void changeOuter() {
                Outer7.this.i += super.j++;
                Outer7.this.saySomething();
            }
        };

        inner7.changeOuter();
        inner7.show();
    }
}
