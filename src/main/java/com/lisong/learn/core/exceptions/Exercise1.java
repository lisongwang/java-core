package com.lisong.learn.core.exceptions;

import static com.lisong.learn.core.util.Print.print;

public class Exercise1 {

    static void f() throws Exception1 {
        print("Throw exception here!");
        throw new Exception1("From f()");
    }

    public static void main(String[] args) {

        try {
            f();
        }catch(Exception e) {
           print("Caught exception " + e + " here!");
            e.printStackTrace();
        }finally {
            print("do finally");
        }
    }
}

class Exception1 extends Exception {
    Exception1(String msg) { super(msg); }
}
