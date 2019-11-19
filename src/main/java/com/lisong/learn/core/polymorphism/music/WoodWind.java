package com.lisong.learn.core.polymorphism.music;

import static com.lisong.learn.core.util.Print.print;

public class WoodWind extends Wind {

    @Override
    public void play(Note n) {
        print("WoodWind.play() " + n);
    }

    @Override
    public void adjust() {
        print("Adjusting WoodWind");
    }
}
