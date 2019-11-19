package com.lisong.learn.core.innerclasses;

import static com.lisong.learn.core.util.Print.print;

public class Exercise26 {

    class InheritInner extends Outer26.Inner26 {

        InheritInner(int i, Outer26 out) {
            out.super(i);
        }
    }

    public static void main(String[] args) {
        Exercise26 exe26 = new Exercise26();
        Outer26 out = new Outer26();
        InheritInner ii = exe26.new InheritInner(1, out);
    }
}

class Outer26 {

    Outer26() {
        print("Constructing Outer26");
    }

    class Inner26 {

        Inner26(int i) {
            print("Constructing Inner26 " + i);
        }
    }
}
