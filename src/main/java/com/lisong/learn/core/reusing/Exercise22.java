package com.lisong.learn.core.reusing;

public class Exercise22 {

    public static void main(String[] args) {

        Dinosaur d = new Dinosaur();
        d.f();
        System.out.println("d.i = " + d.i + " d.j = " + d.j);
    }
}

final class Dinosaur {
    int i = 7;
    int j = 1;
    void f() { i++; j++; }
}

//class Further extends Dinosaur {}