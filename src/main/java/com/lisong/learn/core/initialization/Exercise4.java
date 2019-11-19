package com.lisong.learn.core.initialization;

/**
 * Combine exercise 3, exercise 4.
 */
public class Exercise4 {

    Exercise4 () {
        System.out.println("This is no-arg constructor!");
    }

    Exercise4 (String msg) {
        System.out.println("Hi, " + msg);
    }

    public static void main(String[] args) {

        Exercise4 exe3 = new Exercise4();
        Exercise4 exe4 = new Exercise4("constructor with args!");
    }
}
