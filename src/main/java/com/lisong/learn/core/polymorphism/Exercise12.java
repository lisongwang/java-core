package com.lisong.learn.core.polymorphism;

import com.lisong.learn.core.polymorphism.animal.RandomRodentGenerator;
import com.lisong.learn.core.polymorphism.animal.Rodent;

public class Exercise12 {

    public static void main(String[] args) {

        RandomRodentGenerator gen = new RandomRodentGenerator();
        Rodent[] rods = new Rodent[3];
        for (int i = 0; i < rods.length; i++) {
            rods[i] = gen.nextRodent();
        }
    }
}
