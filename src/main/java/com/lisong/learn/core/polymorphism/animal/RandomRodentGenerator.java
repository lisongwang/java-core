package com.lisong.learn.core.polymorphism.animal;

import java.util.Random;

public class RandomRodentGenerator {

    private Random rand = new Random(20);
    protected SharedMember share = new SharedMember("share");

    public Rodent nextRodent() {

        switch(rand.nextInt(1000) % 3) {
            default:
            case 0: return new Mouse(share);
            case 1: return new Gerbil(share);
            case 2: return new Hamster(share);
        }
    }
}
