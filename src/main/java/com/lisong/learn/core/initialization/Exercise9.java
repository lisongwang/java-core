package com.lisong.learn.core.initialization;

/**
 * Combine exercise 8, exercise 9.
 */
public class Exercise9 {

    private int i;
    private String s;

    Exercise9(int i) {
        this.i = i;
    }
    Exercise9(String s) {
        this.s = s;
    }
    Exercise9(int i, String s) {
        this(i);
        this.s = s;
    }
    Exercise9() {
        this(10, "ten");
    }

    void say() {
        System.out.println("i = " + i + " s = " + s);
    }

    void method1() {
        System.out.println("Do method1!");
    }

    void method2() {
        method1();
        this.method1();
    }

    public static void main(String[] args) {

        Exercise9 exe8 = new Exercise9();
        exe8.method2();
        exe8.say();
    }
}
