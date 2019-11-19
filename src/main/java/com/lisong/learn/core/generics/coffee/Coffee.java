package com.lisong.learn.core.generics.coffee;

import com.lisong.learn.core.util.Factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Coffee {

    private static long count = 0;
    private final long id = count++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }

    private static final List<Factory<? extends Coffee>> factories = new ArrayList<>();
    static {
        Collections.addAll(factories,
                new Latte.Factory(),
                new Mocha.Factory(),
                new Cappuccino.Factory(),
                new Americano.Factory(),
                new Breve.Factory());
    }

    Coffee() {}

    private static Random rand = new Random(88);
    public static Coffee randomCoffee() {
        return factories.get(rand.nextInt(factories.size())).create();
    }
}
