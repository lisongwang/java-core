package com.lisong.learn.core.polymorphism.animal;

import static com.lisong.learn.core.util.Print.print;

public class Characteristic {

    private String s;

    public Characteristic(String s) {
        this.s = s;
        print("Creating " + this);
    }

    public void dispose() { print("Disposing " + this); }

    @Override
    public String toString() {
        return "Characteristic " + s;
    }
}
