package com.lisong.learn.core.polymorphism.animal;

import static com.lisong.learn.core.util.Print.print;

public class Hamster extends Rodent {

    private Characteristic p = new Characteristic(" larger");
    private Description d = new Description(" eat grain");

    public Hamster(SharedMember share) {
        super(share);
        print("Creating Hamster");
    }
    @Override
    public void dispose() {
        print("Disposing Hamster");
        d.dispose();
        p.dispose();
        super.dispose();
    }

    @Override
    public void eat() {
        print("Hamster.eat()");
    }

    @Override
    public void run() {
        print("Hamster.run()");
    }

    @Override
    public void sleep() {
        print("Hamster.sleep()");
    }
}
