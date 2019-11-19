package com.lisong.learn.core.enumerated.util;

import java.util.Random;

public class Enums {
    private static Random rand = new Random(599);
    public static <T extends Enum<T>> T random(Class<T> clazz) {
        return random(clazz.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}