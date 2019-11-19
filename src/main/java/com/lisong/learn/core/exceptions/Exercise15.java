package com.lisong.learn.core.exceptions;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 13, exercise 14, exercise 15.
 */
public class Exercise15 {

    private static Switch sw = new Switch();

    private static void f() throws Exception10, Exception11 {
        //throw new Exception10();
        //throw new Exception11();
        throw new RuntimeException();
    }

    private static void withoutFinal() {
        try{
            sw.on();
            f();
            sw.off();
        }catch(Exception10 e) {
            sw.off();
        }catch(Exception11 e) {
            sw.off();
        }
    }

    private static void withFinal() {
        try {
            print("Entering first try block");
            try {
                print("Entering second try block");
                if(false)
                    throw new Exception11();
                else
                    throw new RuntimeException();
            }finally {
                print("Finally in second try block");
            }
        }catch (Exception11 e) {
            print("Caught exception11 in first block");
        }finally {
            print("Finally in first try block");
        }
    }

    public static void main(String[] args) {
        withoutFinal();
        //withFinal();
    }
}

class Switch {

    private boolean state = false;

    void on() { state = true; print(this); }
    void off() { state = false; print(this); }
    boolean read() { return state; }

    @Override
    public String toString() {
        return state ? "On" : "Off";
    }
}