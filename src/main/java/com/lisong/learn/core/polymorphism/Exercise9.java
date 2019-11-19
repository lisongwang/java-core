package com.lisong.learn.core.polymorphism;

import com.lisong.learn.core.polymorphism.animal.RandomRodentGenerator;
import com.lisong.learn.core.polymorphism.animal.Rodent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Exercise9 {

    public static void main(String[] args) {

        RandomRodentGenerator gen = new RandomRodentGenerator();
        List<Rodent> rods = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            rods.add(gen.nextRodent());
        }

        Iterator<Rodent> it = rods.iterator();
        while(it.hasNext()) {
            Rodent rod = it.next();
            rod.eat();
            rod.run();
            rod.sleep();
        }
    }
}
