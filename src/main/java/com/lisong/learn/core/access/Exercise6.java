package com.lisong.learn.core.access;

/**
 * classes within single source file have default package access to each other.
 */
public class Exercise6 {

    public static void main(String[] args) {

        ProtectClass pc = new ProtectClass("Protected");
        System.out.println(pc.name);
    }
}

class ProtectClass {

    protected String name;
    ProtectClass (String name) { this.name = name; }
}