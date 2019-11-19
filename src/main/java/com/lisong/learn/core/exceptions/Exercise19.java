package com.lisong.learn.core.exceptions;

/**
 * Combine exercise 18, exercise 19.
 */
public class Exercise19 {

    static void f() throws VeryImportantException { throw new VeryImportantException(); }
    static void g() throws HoHumException { throw new HoHumException(); }
    static void t() throws AnotherException { throw new AnotherException(); }

    public static void main(String[] args) {

        try {
            try {
                try {
                    f();
                }catch(Exception e) {
                    e.printStackTrace();
                }finally {
                  g();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }finally {
                t();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

class VeryImportantException extends Exception {}
class HoHumException extends Exception {}
class AnotherException extends Exception {}
