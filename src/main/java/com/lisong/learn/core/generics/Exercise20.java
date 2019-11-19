package com.lisong.learn.core.generics;

import static com.lisong.learn.core.util.Print.print;

public class Exercise20 {

    static <T extends Intf20> void call(T t) {
        t.f();
        t.g();
    }

    public static void main(String[] args) {
        call(new Intf20Impl());
    }
}

interface Intf20 {

    void f();
    void g();
}

class Intf20Impl implements Intf20 {
    @Override
    public void f() {
        print("Intf20Impl.f()");
    }

    @Override
    public void g() {
        print("Intf20Impl.g()");
    }

    public void t() {
        print("Intf20Impl.t()");
    }
}