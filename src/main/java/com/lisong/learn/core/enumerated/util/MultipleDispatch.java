package com.lisong.learn.core.enumerated.util;

import static com.lisong.learn.core.util.Print.print;

public class MultipleDispatch {

    public static void main(String[] args) {
        A a= new A1();
        A aa = new A2();
        B b = new B1();
        B bb = new B2();
        a.plus(b);
        a.plus(bb);
        aa.plus(b);
        aa.plus(bb);
    }
}

interface A {
    void plus(B b);
}
interface B {
    void plusplus(A1 a);
    void plusplus(A2 a);
}

class B1 implements B {
    public void plusplus(A1 a) {
        print("B1 plus A1");
    }
    public void plusplus(A2 a) {
        print("B1 plus A2");
    }
}
class B2 implements B {
    public void plusplus(A1 a) {
        print("B2 plus A1");
    }
    public void plusplus(A2 a) {
        print("B2 plus A2");
    }
}
class A1 implements A {
    @Override
    public void plus(B b) {
        b.plusplus(this);
    }
}
class A2 implements A {
    @Override
    public void plus(B b) {
        b.plusplus(this);
    }
}