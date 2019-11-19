package com.lisong.learn.core.polymorphism.animal;

import static com.lisong.learn.core.util.Print.print;

public class Gerbil extends Rodent {

    private Characteristic p = new Characteristic(" live in sand");

    private Description d = new Description(" eat something");

    public Gerbil(SharedMember share) {
        super(share);
        print("Creating Gerbil");
    }
    @Override
    public void dispose() {
        print("Disposing Gerbil");
        d.dispose();
        p.dispose();
        super.dispose();
    }

    @Override
    public void eat() {
        print("Gerbil.eat()");
    }

    @Override
    public void run() {
        print("Gerbil.run()");
    }

    @Override
    public void sleep() {
        print("Gerbil.sleep()");
    }
}
