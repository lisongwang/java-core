package com.lisong.learn.core.exceptions;

import java.util.Random;

public class Exercise9 {

    static void f(int i) throws Exception1, Exception4, Exception9 {
        System.out.println(i);
        switch(i) {
            default:
            case 0: throw new Exception1("0");
            case 1: throw new Exception4("4");
            case 2: throw new Exception9("2");
        }
    }

    public static void main(String[] args) {

        Random rand = new Random();
        Integer[] ints = new Integer[1];
        try {
            f(ints[0]);
            f(rand.nextInt(6));
        }catch(Exception e) {
            System.out.println("Caught all the exceptions here!");
            e.printStackTrace();
        }finally {
            System.out.println("Always finally here!");
        }
    }
}

class Exception9 extends Exception {
    Exception9(String msg) { super(msg); }
}
