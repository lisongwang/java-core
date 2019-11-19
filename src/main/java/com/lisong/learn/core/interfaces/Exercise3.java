package com.lisong.learn.core.interfaces;

/**
 * Combine exercise 1, exercise 2, exercise 3.
 */
public abstract class Exercise3 {

    public static void main(String[] args) {

        //Exercise3 exe2 = new Exercise3();

        Sub3 sub = new Sub3();
        sub.print();
    }
}

abstract class Base3 {

    Base3() {
        print();
    }

    abstract void print();
}

class Sub3 extends Base3 {

    private int i = 10;
    private static final int j = 10;

    @Override
    void print() {

        System.out.println("i = " + i);
        System.out.println("j = " + j);
    }
}
