package com.lisong.learn.core.interfaces;

public class Exercise4 {

    public static void main(String[] args) {

        play(new Sub4());
    }

    private static void play(Base4 base) {
        base.play();
    }
}

abstract class Base4 {
    abstract void play();
}

class Sub4 extends Base4 {

    @Override
    void play() { System.out.println("Do something in Sub4"); }
}