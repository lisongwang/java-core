package com.lisong.learn.core.polymorphism.music;

import static com.lisong.learn.core.util.Print.print;

public class Wind extends Instrument {

    @Override
    public void play(Note n) {
        print("Wind.play() " + n);
    }

    @Override
    public String toString() {
        return "Wind";
    }

    @Override
    public void adjust() {
        print("Adjusting Wind");
    }
}
