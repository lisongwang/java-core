package com.lisong.learn.core.object;

/**
 * Count the bytes of a String!
 */
public class Exercise6 {

    int storage(String s) {

        return s.length() * 2;
    }

    public static void main(String[] args) {

        Exercise6 exe6 = new Exercise6();
        String s = "Count the bytes of a String!";
        System.out.println(s.length());
        System.out.println(exe6.storage(s));
    }
}
