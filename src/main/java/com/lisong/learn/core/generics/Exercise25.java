package com.lisong.learn.core.generics;

import static com.lisong.learn.core.util.Print.print;

public class Exercise25 {

    static <T extends Intf251> void call1(T t) {
        t.f();
    }

    static <T extends Intf252> void call2(T t) {
        t.g();
    }

    public static void main(String[] args) {

        Intf25Impl intf25 = new Intf25Impl();
        call1(intf25);
        call2(intf25);
    }
}

interface Intf251 {
    void f();
}

interface Intf252 {
    void g();
}

class Intf25Impl implements Intf251, Intf252 {
    @Override
    public void f() {
        print("Intf25Impl.f()");
    }

    @Override
    public void g() {
        print("Intf25Impl.g()");
    }
}
