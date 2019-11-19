package com.lisong.learn.core.access;


/**
 * Just show how default access on class work.
 */
public class Exercise9 {

    public static void main(String[] args) {

        PackagedClass pc = new PackagedClass();
    }
}

class PackagedClass {

    public PackagedClass() { System.out.println("Creating a packaged class!");}
}