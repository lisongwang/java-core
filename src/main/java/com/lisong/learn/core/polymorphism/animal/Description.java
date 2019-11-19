package com.lisong.learn.core.polymorphism.animal;

import static com.lisong.learn.core.util.Print.print;

public class Description {

    private String s;

    public Description(String s) {
        this.s = s;
        print("Creating " + this);
    }

    public void dispose() { print("Disposing " + this); }

    @Override
    public String toString() {
        return "Description " + s;
    }
}
