package com.lisong.learn.core.polymorphism.food;

import static com.lisong.learn.core.util.Print.print;

public class Sandwich extends PortableLunch implements FastFood {

    private Bread b = new Bread();
    private Cheese c = new Cheese();
    private Lettuce l = new Lettuce();
    private Pickle p = new Pickle();

    public Sandwich() { print("Sandwich()"); }

    @Override
    public void quickCook() {
        print("quickCook!");
    }
}
