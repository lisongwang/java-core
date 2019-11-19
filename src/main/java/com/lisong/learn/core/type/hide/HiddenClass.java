package com.lisong.learn.core.type.hide;

import static com.lisong.learn.core.util.Print.print;

public class HiddenClass {

    protected void f() { print("Protected.f()"); }
    void g(int i) { print("Package.g() with " + i); }
    private void t(String s, boolean b) throws IllegalArgumentException {
        print("Private.t() with " + s);
        if(!b)
            throw new IllegalArgumentException();
    }
}
