package com.lisong.learn.core.operators;

/**
 * Show the aliasing during assign.
 */
public class Exercise2 {

    public static void main(String[] args) {

        Tank t1 = new Tank(5.5f);
        Tank t2 = new Tank(8.8f);

        System.out.println("1: t1: " + t1.fl + " t2: " + t2.fl);

        t1 = t2;

        System.out.println("2: t1: " + t1.fl + " t2: " + t2.fl);

        t1.fl = 9.9f;

        System.out.println("3: t1: " + t1.fl + " t2: " + t2.fl);

    }
}

class Tank {

    float fl;

    Tank(float f) {

        fl = f;
    }
}
