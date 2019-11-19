package com.lisong.learn.core.polymorphism.music;

import static com.lisong.learn.core.util.Print.print;

public class Percussion extends Instrument {

    @Override
    public void play(Note n) {
        print("Percussion.play() " + n);
    }

    @Override
    public String toString() {
        return "Percussion";
    }

    @Override
    public void adjust() {
        print("Adjusting Percussion");
    }
}
