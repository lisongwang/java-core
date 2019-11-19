package com.lisong.learn.core.util;

import java.util.Random;

public class RandomGenerator {

    private static Random rand = new Random(618);

    public static class Boolean implements Generator<java.lang.Boolean> {

        @Override
        public java.lang.Boolean next() {
            return rand.nextBoolean();
        }
    }

    public static class Byte implements Generator<java.lang.Byte> {

        @Override
        public java.lang.Byte next() {
            return (byte)rand.nextInt(128);
        }
    }

    public static class Short implements Generator<java.lang.Short> {

        @Override
        public java.lang.Short next() {
            return (short)rand.nextInt(65536);
        }
    }

    public static class Character implements Generator<java.lang.Character> {

        @Override
        public java.lang.Character next() {
            return CountingGenerator.chars[rand.nextInt(CountingGenerator.chars.length)];
        }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        private int bound = 1000000;

        public Integer() {}

        public Integer(int bound) {
            this.bound = bound;
        }

        @Override
        public java.lang.Integer next() {
            return rand.nextInt(bound);
        }
    }

    public static class Long implements Generator<java.lang.Long> {

        @Override
        public java.lang.Long next() {
            return rand.nextLong();
        }
    }

    public static class Float implements Generator<java.lang.Float> {

        @Override
        public java.lang.Float next() {
            return rand.nextFloat();
        }
    }

    public static class Double implements Generator<java.lang.Double> {

        @Override
        public java.lang.Double next() {
            return rand.nextDouble();
        }
    }

    public static class String extends CountingGenerator.String {

        {chars = new RandomGenerator.Character();}

        public String() {}

        public String(int length) {
            super(length);
        }
    }
}
