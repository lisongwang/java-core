package com.lisong.learn.core.polymorphism.music;

import static com.lisong.learn.core.util.Print.print;

public class Stringed extends Instrument {

    @Override
    public void play(Note n) {
        print("Stringed.play() " + n);
    }

    @Override
    public String toString() {
        return "Stringed";
    }

    @Override
    public void adjust() {
        print("Adjusting Stringed");
    }
}
