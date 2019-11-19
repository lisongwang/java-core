package com.lisong.learn.core.polymorphism.animal;

import static com.lisong.learn.core.util.Print.print;

public class LivingCreature {

    private Characteristic c = new Characteristic("is alive");
    private Description d = new Description("Basic living Creature");

    public LivingCreature() { print("LivingCreature()"); }

    public void dispose() {
        print("LivingCreature dispose");
        d.dispose();
        c.dispose();
    }
}
