package com.lisong.learn.core.generics;

import com.lisong.learn.core.util.Generator;
import com.lisong.learn.core.util.Generators;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise18 {

    static void eat(BigFish big, LittleFish little) {
        print(big + " eat " + little);
    }

    public static void main(String[] args) {

        List<BigFish> bigs = new ArrayList<>();
        Generators.fill(bigs, BigFish.generate(), 5);
        Queue<LittleFish> littles = new LinkedList<>();
        Generators.fill(littles, LittleFish.generate, 20);
        Random rand = new Random(77);
        for(LittleFish f : littles)
            eat(bigs.get(rand.nextInt(bigs.size())), f);
    }
}


class BigFish {

    private static long count = 1;
    private final long id = count++;

    @Override
    public String toString() {
        return "Big fish " + id;
    }

    static Generator<BigFish> generate() {
        return () -> new BigFish();
    }
}

class LittleFish{

    private static long count = 1;
    private final long id = count++;

    @Override
    public String toString() {
        return "Little fish " + id;
    }

    static Generator<LittleFish> generate = () -> new LittleFish();
}