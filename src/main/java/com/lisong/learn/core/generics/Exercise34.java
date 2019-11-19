package com.lisong.learn.core.generics;

import static com.lisong.learn.core.util.Print.print;

public class Exercise34 {

    public static void main(String[] args) {
        A a = new A();
        a.doSomething(a);
    }
}

abstract class SelfBound<T extends SelfBound<T>> {

    abstract T setAndGet(T t);

    T doSomething(T t) {
        print("doSomething in SelfBound.");
        return setAndGet(t);
    }
}

class A extends SelfBound<A> {
    @Override
    A setAndGet(A a) {
        print("setAndGet in A.");
        return a;
    }
}