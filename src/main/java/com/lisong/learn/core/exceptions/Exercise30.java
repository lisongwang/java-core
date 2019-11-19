package com.lisong.learn.core.exceptions;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 29, exercise 30.
 */
public class Exercise30 {

    static void throwRuntimeException(int i) {
        try {
            switch(i) {
                case 0: throw new Sneeze();
                case 1: throw new Annoyance();
                case 2: throw new RuntimeException("Where am I?");
                default: return;
            }
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 4; i++) {
            try {
                if(i < 3)
                    throwRuntimeException(i);
                else
                    throw new SomeOtherException();
            }catch(SomeOtherException e) {
                print("Caught SomeOtherException " + e);
            }catch(RuntimeException e) {
                try {
                    throw e.getCause();
                }catch(Sneeze s) {
                    print("Caught Sneeze " + s);
                }catch(Annoyance a) {
                    print("Caught Annoyance " + a);
                }catch(Throwable t) {
                    print("Caught Throwable " + t);
                }
            }
        }
    }
}

class Annoyance extends RuntimeException {}
class Sneeze extends Annoyance {}
class SomeOtherException extends Exception {}