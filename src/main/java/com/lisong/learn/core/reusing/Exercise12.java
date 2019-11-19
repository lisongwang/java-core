package com.lisong.learn.core.reusing;

/**
 * Add dispose to exercise 10.
 */
public class Exercise12 {

    public static void main(String[] args) {

        Stem s = new Stem(2.73);

        try {
            // do something!
        } finally {
            s.dispose();
        }
    }
}
