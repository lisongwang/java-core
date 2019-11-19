package com.lisong.learn.core.reusing;

import static com.lisong.learn.core.util.Print.print;

public class Exercise5 {

    public static void main(String[] args) {
        C c = new C();
    }
}

class A {

    A(int i) { print("A()"); }
}

class B extends A{

    B(int i) {
        super(i);
        print("B()"); }
}

class C extends A {

    B b = new B(11);

    C() {
        super(1);
        print("C()");
    }
}
