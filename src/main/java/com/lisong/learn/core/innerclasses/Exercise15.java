package com.lisong.learn.core.innerclasses;

import static com.lisong.learn.core.util.Print.print;

public class Exercise15 {

    static Outer15 createOuter(int j) {
        return new Outer15(1) {
            private int i;
            {
                this.i = j;
            }

            @Override
            void show() {
                super.i += this.i;
                print("Inner15.i = " + this.i);
                super.show();
            }
        };
    }

    public static void main(String[] args) {

        Outer15 out = createOuter(3);
        out.show();
    }
}

class Outer15 {

    protected int i;

    Outer15(int i) {
        this.i = i;
        print("Constructing Outer15 as " + i);
    }

    void show() {
        print("Outer15.i = " + i);
    }
}