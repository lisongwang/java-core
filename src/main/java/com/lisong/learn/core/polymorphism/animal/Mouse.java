package com.lisong.learn.core.polymorphism.animal;

import static com.lisong.learn.core.util.Print.print;

public class Mouse extends Rodent {

    private Characteristic p = new Characteristic(" like cheese");
    private Description d = new Description(" nocturnal");
    public Mouse(SharedMember share) {
        super(share);
        print("Creating Mouse");
    }
    @Override
    public void dispose() {
        print("Disposing Mouse");
        d.dispose();
        p.dispose();
        super.dispose();
    }

    @Override
    public void eat() {
        print("Mouse.eat()");
    }

    @Override
    public void run() {
        print("Mouse.run()");
    }

    @Override
    public void sleep() {
        print("Mouse.sleep()");
    }
}
