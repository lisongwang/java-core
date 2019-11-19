package com.lisong.learn.core.initialization;

/**
 * Combine exercise 1, exercise 2.
 */
public class Exercise2 {

    String s;

    Exercise2 () {
        this.s1 = "string in constructor";
    }

    String s1 = "string in definition";

    public static void main(String[] args) {

        Exercise2 exe1 = new Exercise2();
        System.out.println(exe1.s == null?true:false);

        Exercise2 exe2 = new Exercise2();
        System.out.println(exe2.s1);
    }
}
