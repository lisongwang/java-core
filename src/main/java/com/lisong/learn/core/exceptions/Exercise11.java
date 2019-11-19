package com.lisong.learn.core.exceptions;

/**
 * Combine exercise10, exercise11
 */
public class Exercise11 {

    void f() {
        try {
            t();
        }catch(Exception11 e) {
            throw new RuntimeException(e);
        }
    }

    void g() throws Exception10 {
        throw new Exception10();
    }

    void t() throws Exception11 {
        try{
            g();
        }catch(Exception10 e) {
            Exception11 e11 =  new Exception11();
            e11.initCause(e);
            throw e11;
        }
    }

    public static void main(String[] args) {
        Exercise11 exe11 = new Exercise11();
        exe11.f();
    }
}

class Exception10 extends Exception {}
class Exception11 extends Exception {}
