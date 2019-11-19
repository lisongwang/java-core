package com.lisong.learn.core.polymorphism.animal;

import static com.lisong.learn.core.util.Print.print;

public class Amphibian extends Animal {

    private Characteristic c = new Characteristic("can live in water");

    private Description d = new Description("Both water and land");

    public Amphibian() { print("Amphibian()"); }

    @Override
    public void dispose() {
        print("Amphibian dispose");
        d.dispose();
        c.dispose();
        super.dispose();
    }
}
