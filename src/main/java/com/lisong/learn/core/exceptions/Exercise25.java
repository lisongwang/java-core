package com.lisong.learn.core.exceptions;

import static com.lisong.learn.core.util.Print.print;

public class Exercise25 {

    public static void main(String[] args) {

        A c = new C();
        try {
            c.f();
        }catch(ThirdLevelException e) {
            print("Caught ThirdLevelException");
        }catch(SecondLevelException e) {
            print("Caught SecondLevelException");
        }catch(FirstLevelException e) {
            print("Caught FirstLevelException");
        }
    }
}

class FirstLevelException extends Exception {}
class SecondLevelException extends FirstLevelException {}
class ThirdLevelException extends SecondLevelException {}

class A {
    void f() throws FirstLevelException { throw new FirstLevelException(); }
}

class B extends A {
    @Override
    void f() throws SecondLevelException { throw new SecondLevelException(); }
}

class C extends B {
    @Override
    void f() throws ThirdLevelException { throw new ThirdLevelException(); }
}