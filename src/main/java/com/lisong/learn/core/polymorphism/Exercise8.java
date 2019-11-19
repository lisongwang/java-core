package com.lisong.learn.core.polymorphism;

import com.lisong.learn.core.polymorphism.music.*;

/**
 * Combine exercise 6, exercise 7, exercise 8.
 */
public class Exercise8 {

    private static void tune(Playable i) {
        i.play(Note.MIDDLE_C);
    }
    private static void tuneAll(Playable[] ins) {
        for (Playable i : ins) {
            tune(i);
        }
    }
    private static void printAll(Playable[] ins) {
        for (Playable i : ins) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {

        Playable[] ins = new Playable[] {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new WoodWind(),
                new Piano()
        };

        //tuneAll(ins);
        //printAll(ins);

        RandomMusicGenerator reg = new RandomMusicGenerator();
        Playable[] ins_2 = new Playable[5];
        for (int i = 0; i < ins_2.length; i++) {
            ins_2[i] = reg.nextMusic();
        }

        tuneAll(ins_2);
        printAll(ins_2);
    }
}
