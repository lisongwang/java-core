package com.lisong.learn.core.polymorphism.animal;

import static com.lisong.learn.core.util.Print.print;

public class Frog extends Amphibian {

    private Characteristic c = new Characteristic("Croaks");

    private Description d = new Description("Eats Bugs");

    public Frog() { print("Frog()"); }

    @Override
    public void dispose() {
        print("Frog dispose");
        d.dispose();
        c.dispose();
        super.dispose();
    }
}
