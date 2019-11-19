package com.lisong.learn.core.reusing;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 20, exercise 21.
 */
public class Exercise21 {

    public static void main(String[] args) {
        OverridingPrivate op = new OverridingPrivate();
        op.f();
        op.g();
        WithFinals wf = op;
        //wf.f();
        //wf.g();
    }
}

class WithFinals {

    private void f() { print("WithFinals.f()"); }

    private final void g() { print("WithFinals.g()"); }
}

class OverridingPrivate extends WithFinals {

    //@Override
    public void f() { print("OverridingPrivate.f()"); }

    public void g() { print("OverridingPrivate.g()"); }
}