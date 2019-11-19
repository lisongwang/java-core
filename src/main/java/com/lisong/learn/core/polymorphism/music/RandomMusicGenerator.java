package com.lisong.learn.core.polymorphism.music;

import java.util.Random;

public class RandomMusicGenerator {

    private Random rand = new Random(120);

    public Instrument nextMusic() {

        switch(rand.nextInt(6)) {
            default:
            case 0: return new Wind();
            case 1: return new Percussion();
            case 2: return new Stringed();
            case 3: return new Brass();
            case 4: return new WoodWind();
            case 5: return new Piano();
        }
    }
}
