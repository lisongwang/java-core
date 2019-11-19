package com.lisong.learn.core.reusing;

import java.util.Random;

/**
 * Show the difference between static final and final.
 */
public class Exercise18 {

    private static final Random rand = new Random(47);
    private String name;

    Exercise18(String name) { this.name = name; }

    public static final int ID_1 = rand.nextInt(100);
    public final int id_2 = rand.nextInt(100);

    @Override
    public String toString() {
        return name + " ID_1 = " + ID_1 + " id_2 = " + id_2;
    }

    public static void main(String[] args) {

        Exercise18 exe18_1 = new Exercise18("fd1");
        System.out.println(exe18_1);
        Exercise18 exe18_2 = new Exercise18("fd2");
        System.out.println(exe18_1);
        System.out.println(exe18_2);
    }
}
