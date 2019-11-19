package com.lisong.learn.core.polymorphism.animal;

import static com.lisong.learn.core.util.Print.print;

public class Animal extends LivingCreature {

    private Characteristic c = new Characteristic("has heart");
    private Description d = new Description("Animal not Vegetable");

    public Animal() { print("Animal()"); }

    @Override
    public void dispose() {
        print("Animal dispose");
        d.dispose();
        c.dispose();
        super.dispose();
    }
}
