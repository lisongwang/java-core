package com.lisong.learn.core.polymorphism.music;

import static com.lisong.learn.core.util.Print.print;

public class Piano extends Instrument {

    @Override
    public void play(Note n) {
        print("Piano.play() " + n);
    }

    @Override
    public String toString() {
        return "Piano";
    }

    @Override
    public void adjust() {
        print("Adjusting Piano");
    }
}
