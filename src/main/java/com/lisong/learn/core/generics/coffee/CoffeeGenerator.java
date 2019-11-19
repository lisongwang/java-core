package com.lisong.learn.core.generics.coffee;

import com.lisong.learn.core.util.Generator;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {

    private int size = 0;
    public CoffeeGenerator() {}
    public CoffeeGenerator(int size) { this.size = size; }

    private static final List<Class<? extends Coffee>> types = new ArrayList<>();
    static {
        Collections.addAll(types,
                Latte.class,
                Mocha.class,
                Cappuccino.class,
                Americano.class,
                Breve.class);
    }

    private Random rand = new Random(76);
    @Override
    public Coffee next() {
        Coffee c = null;
        try {
            c = types.get(rand.nextInt(types.size())).newInstance();
        } catch (InstantiationException e) {
            print("Can not initiate the class");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new Iterator<Coffee>() {
            private int count = size;
            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public Coffee next() {
                count--;
                return CoffeeGenerator.this.next();
            }
        };
    }
}